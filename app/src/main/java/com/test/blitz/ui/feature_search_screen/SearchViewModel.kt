package com.test.blitz.ui.feature_search_screen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.test.blitz.core.mvi.BaseViewModel
import com.test.blitz.core.mvi.Reducer
import com.test.blitz.core.mvi.UiEvent
import com.test.blitz.core.mvi.UiState
import com.test.blitz.core.resource
import com.test.blitz.di.IoDispatcher
import com.test.blitz.domain.use_cases.SearchPhotos
import com.test.blitz.domain.use_cases.SearchUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchPhotos: SearchPhotos,
    val searchUsers: SearchUsers,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : BaseViewModel<SearchUiState, SearchUiEvent>() {

    private val reducer = SearchReducer()

    override val state
        get() = reducer.state

    private var photoSearchJob: Job? = null
    private var userSearchJob: Job? = null

    fun search(search: String) {
        sendEvent(SearchUiEvent.Search(search))

        photoSearchJob?.cancel()
        photoSearchJob = viewModelScope.launch(dispatcher) {
            delay(500)
            if (!isActive || search.isEmpty()) return@launch
            sendEvent(SearchUiEvent.IsLoading(true))
            resource({ searchPhotos(search) }) {
                onSuccess {
                    sendEvent(SearchUiEvent.PhotosResult(it))
                }
                onError {
                    Log.e("SearchViewModel", "Error", it)
                    sendEvent(SearchUiEvent.Error(it))
                }
            }
        }

        userSearchJob?.cancel()
        userSearchJob = viewModelScope.launch(dispatcher) {
            delay(500)
            if (!isActive || search.isEmpty()) return@launch
            sendEvent(SearchUiEvent.IsLoading(true))
            resource({ searchUsers(search) }) {
                onSuccess {
                    sendEvent(SearchUiEvent.UsersResult(it))
                }
                onError {
                    Log.e("SearchViewModel", "Error", it)
                    sendEvent(SearchUiEvent.Error(it))
                }
            }
        }
    }

    private fun sendEvent(event: SearchUiEvent) {
        reducer.sendEvent(event)
    }

    fun onDismissError() {
        sendEvent(SearchUiEvent.Error(null))
    }

    private class SearchReducer(initial: SearchUiState = SearchUiState()) : Reducer<SearchUiState, SearchUiEvent>(initial) {
        override fun reduce(oldState: SearchUiState, event: SearchUiEvent) {
            setState(
                when (event) {
                    is SearchUiEvent.Search -> {
                        oldState.copy(
                            query = event.query,
                        )
                    }
                    is SearchUiEvent.PhotosResult -> {
                        oldState.copy(
                            isLoading = false,
                            photos = event.photos
                        )
                    }
                    is SearchUiEvent.UsersResult -> {
                        oldState.copy(
                            isLoading = false,
                            users = event.users,
                        )
                    }
                    is SearchUiEvent.Error -> {
                        oldState.copy(
                            isLoading = false,
                            error = event.e,
                        )
                    }
                    is SearchUiEvent.Clear -> {
                        SearchUiState()
                    }
                    is SearchUiEvent.IsLoading -> {
                        oldState.copy(
                            isLoading = event.isLoading
                        )
                    }
                }
            )
        }
    }
}