package com.test.blitz.ui.feature_home_screen

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.blitz.core.mvi.BaseViewModel
import com.test.blitz.core.mvi.Navigator
import com.test.blitz.core.mvi.Reducer
import com.test.blitz.core.mvi.SharedNavigator
import com.test.blitz.core.resource
import com.test.blitz.di.IoDispatcher
import com.test.blitz.domain.use_cases.GetPhotos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotos: GetPhotos,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<HomeUiState, HomeUiEvent>(),
    Navigator<HomeNavTarget> by SharedNavigator() {

    private val reducer = HomeReducer(HomeUiState())

    override val state: StateFlow<HomeUiState>
        get() = reducer.state

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch(dispatcher) {
            sendEvent(HomeUiEvent.Loading(true))
            resource({ getPhotos() }) {
                onSuccess {
                    reducer.sendEvent(HomeUiEvent.ShowPhotos(it))
                }
                onError {
                    Log.e("HomeViewModel", "Error loading photos", it)
                    reducer.sendEvent(HomeUiEvent.Error(it))
                }
                onComplete {
                    sendEvent(HomeUiEvent.Loading(false))
                }
            }
        }
    }

    private fun sendEvent(event: HomeUiEvent) {
        reducer.sendEvent(event)
    }

    private class HomeReducer(initial: HomeUiState) : Reducer<HomeUiState, HomeUiEvent>(initial) {
        override fun reduce(oldState: HomeUiState, event: HomeUiEvent) {
            setState(when (event) {
                is HomeUiEvent.PhotoClicked -> {
                   oldState.copy()
                }
                is HomeUiEvent.Loading -> {
                    oldState.copy(
                        isLoading = event.isLoading
                    )
                }
                is HomeUiEvent.ShowPhotos -> {
                    oldState.copy(
                        photos = event.photos,
                    )
                }
                is HomeUiEvent.Error -> {
                    oldState.copy(
                    )
                }
            })
        }

    }
}