package com.test.blitz.ui.feature_home_screen

import com.test.blitz.core.mvi.BaseViewModel
import com.test.blitz.core.mvi.Reducer

class HomeViewModel : BaseViewModel {
    val state = HomeUiState()

    private class HomeReducer : Reducer<HomeUiState, HomeUiEvent>(HomeUiState()) {
        override fun reduce(state: HomeUiState, event: HomeUiEvent): HomeUiState {
            return state
        }
    }
}