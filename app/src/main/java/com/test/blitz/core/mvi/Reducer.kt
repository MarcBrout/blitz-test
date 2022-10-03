package com.test.blitz.core.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Reducer<State : UiState, Event : UiEvent>(initial: State) {

    private val _state: MutableStateFlow<State> = MutableStateFlow(initial)
    val state: StateFlow<State>
        get() = _state

    fun sendEvent(event: Event) {
        reduce(_state.value, event)
    }

    fun setState(newState: State) {
        val success = _state.tryEmit(newState)
    }

    abstract fun reduce(oldState: State, event: Event)
}

interface UiState
interface UiEvent