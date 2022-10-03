package com.test.blitz.core.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State: UiState, in Event: UiEvent>: ViewModel() {
    abstract val state: StateFlow<State>
}