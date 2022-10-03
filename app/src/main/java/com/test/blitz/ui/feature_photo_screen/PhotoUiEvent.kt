package com.test.blitz.ui.feature_photo_screen

import com.test.blitz.core.mvi.UiEvent
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.Statistics

sealed class PhotoUiEvent : UiEvent {
    class Error(val error: Throwable?) : PhotoUiEvent()
    class PhotoLoading(val isLoading: Boolean) : PhotoUiEvent()
    class PhotoDetails(val photo: Photo) : PhotoUiEvent()
    class UserPhotosLoading(val isLoading: Boolean) : PhotoUiEvent()
    class UserPhotos(val photos: List<Photo>) : PhotoUiEvent()
    class PhotoStatisticsLoading(val isLoading: Boolean) : PhotoUiEvent()
    class PhotoStatistics(val statistics: Statistics) : PhotoUiEvent()
}