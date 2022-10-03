package com.test.blitz.ui.feature_home_screen

import com.test.blitz.core.mvi.UiState
import com.test.blitz.domain.models.Photo

data class HomeUiState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = listOf(),
) : UiState