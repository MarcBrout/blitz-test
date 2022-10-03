package com.test.blitz.ui.feature_home_screen

import com.test.blitz.core.mvi.UiEvent
import com.test.blitz.domain.models.Photo

sealed class HomeUiEvent : UiEvent {
    class Loading(val isLoading: Boolean) : HomeUiEvent()
    class ShowPhotos(val photos: List<Photo>) : HomeUiEvent()
    class PhotoClicked(val photoId: String) : HomeUiEvent()
}