package com.test.blitz.ui.feature_photo_screen

import com.test.blitz.core.mvi.UiState
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.Statistics


data class PhotoUiState(
    val photoLoading: Boolean = false,
    val photo: Photo? = null,
    val userPhotoLoading: Boolean = false,
    val userPhotos: List<Photo> = emptyList(),
    val statisticsLoading: Boolean = false,
    val statistics: Statistics? = null,
    val error: Throwable? = null,
) : UiState