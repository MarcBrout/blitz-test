package com.test.blitz.core.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface NavTarget
interface Navigator<T : NavTarget> {
    val navigation: Flow<T>
    fun navigateTo(navTarget: T)
}

class SharedNavigator<T: NavTarget> : Navigator<T> {
    private val _sharedFlow = MutableSharedFlow<T>(extraBufferCapacity = 1)
    override val navigation = _sharedFlow.asSharedFlow()

    override fun navigateTo(navTarget: T) {
        _sharedFlow.tryEmit(navTarget)
    }
}

