package com.test.blitz.ui.feature_home_screen

import com.test.blitz.core.mvi.BaseViewModel
import com.test.blitz.core.mvi.Navigator
import com.test.blitz.core.mvi.Reducer
import com.test.blitz.core.mvi.SharedNavigator
import com.test.blitz.di.IoDispatcher
import com.test.blitz.domain.use_cases.GetPhotos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
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
                        isLoading = false,
                    )
                }
            })
        }

    }
}