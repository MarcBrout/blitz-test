package com.test.blitz.ui.feature_home_screen

import com.test.blitz.core.mvi.UiEvent

sealed class HomeUiEvent : UiEvent {
    class PhotoClicked(val photoId: String) : HomeUiEvent()
}