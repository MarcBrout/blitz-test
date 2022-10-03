package com.test.blitz.ui.feature_photo_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.test.blitz.core.mvi.BaseViewModel
import com.test.blitz.core.mvi.Reducer
import com.test.blitz.core.resource
import com.test.blitz.di.IoDispatcher
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.use_cases.GetPhoto
import com.test.blitz.domain.use_cases.GetPhotoStatistics
import com.test.blitz.domain.use_cases.GetUserPhotos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val getPhoto: GetPhoto,
    private val getUserPhotos: GetUserPhotos,
    private val getPhotoStatistics: GetPhotoStatistics,
    private val savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : BaseViewModel<PhotoUiState, PhotoUiEvent>() {
    private val reducer = PhotoReducer()

    private val photoId: String = checkNotNull(savedStateHandle["photoId"])

    override val state: StateFlow<PhotoUiState>
        get() = reducer.state

    init {
        loadPhoto(photoId)
    }

    private fun loadPhoto(id: String) {
        viewModelScope.launch(dispatcher) {
            sendEvent(PhotoUiEvent.PhotoLoading(true))
            resource({ getPhoto(id) }) {
                onSuccess { photo ->
                    sendEvent(PhotoUiEvent.PhotoDetails(photo))
                    loadUserPhotos(photo.user.username)
                    loadPhotoStatistics(photo.id)
                }
                onError { error ->
                    Log.e("PhotoViewModel", "Error loading photo", error)
                    sendEvent(PhotoUiEvent.Error(error))
                }
                onComplete {
                    sendEvent(PhotoUiEvent.PhotoLoading(false))
                }
            }
        }
    }

    private fun loadUserPhotos(id: String) {
        viewModelScope.launch(dispatcher) {
            sendEvent(PhotoUiEvent.UserPhotosLoading(true))
            resource({ getUserPhotos(id) }) {
                onSuccess { photos ->
                    sendEvent(PhotoUiEvent.UserPhotos(photos.filter { it.id != photoId }))
                }
                onError { error ->

                    sendEvent(PhotoUiEvent.Error(error))
                }
                onComplete {
                    sendEvent(PhotoUiEvent.UserPhotosLoading(false))
                }
            }
        }
    }

    private fun loadPhotoStatistics(id: String) {
        viewModelScope.launch(dispatcher) {
            sendEvent(PhotoUiEvent.PhotoStatisticsLoading(true))
            resource({ getPhotoStatistics(id) }) {
                onSuccess { statistics ->
                    sendEvent(PhotoUiEvent.PhotoStatistics(statistics))
                }
                onError { error ->
                    Log.e("PhotoViewModel", "Error loading photo statistics", error)
                    sendEvent(PhotoUiEvent.Error(error))
                }
                onComplete {
                    sendEvent(PhotoUiEvent.PhotoStatisticsLoading(false))
                }
            }
        }
    }

    private fun sendEvent(event: PhotoUiEvent) {
        reducer.sendEvent(event)
    }

    fun onDismissError() {
        sendEvent(PhotoUiEvent.Error(null))
    }

    fun showFullScreen(photo: Photo?) {
        sendEvent(PhotoUiEvent.ShowFullScreen(photo))
    }

    private class PhotoReducer(initial: PhotoUiState = PhotoUiState()) : Reducer<PhotoUiState, PhotoUiEvent>(initial) {
        override fun reduce(oldState: PhotoUiState, event: PhotoUiEvent) {
            setState(
                when (event) {
                    is PhotoUiEvent.Error -> {
                        oldState.copy(
                            error = event.error,
                        )
                    }
                    is PhotoUiEvent.PhotoLoading -> {
                        oldState.copy(
                            photoLoading = event.isLoading,
                        )
                    }
                    is PhotoUiEvent.PhotoDetails -> {
                        oldState.copy(
                            photo = event.photo,
                        )
                    }
                    is PhotoUiEvent.PhotoStatistics -> {
                        oldState.copy(
                            statistics = event.statistics,
                        )
                    }
                    is PhotoUiEvent.PhotoStatisticsLoading -> {
                        oldState.copy(
                            statisticsLoading = event.isLoading,
                        )
                    }
                    is PhotoUiEvent.UserPhotosLoading -> {
                        oldState.copy(
                            userPhotoLoading = event.isLoading,
                        )
                    }
                    is PhotoUiEvent.UserPhotos -> {
                        oldState.copy(
                            userPhotos = event.photos,
                        )
                    }
                    is PhotoUiEvent.ShowFullScreen -> {
                        oldState.copy(
                            showPhotoFullScreen = event.photo,
                        )
                    }
                }
            )
        }
    }
}