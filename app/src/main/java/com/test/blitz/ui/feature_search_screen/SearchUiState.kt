package com.test.blitz.ui.feature_search_screen

import com.test.blitz.core.mvi.UiState
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.User

data class SearchUiState(
    val isLoading: Boolean = false,
    val query: String = "",
    val photos: List<Photo> = emptyList(),
    val users: List<User> = emptyList(),
    val error: Throwable? = null,
): UiState