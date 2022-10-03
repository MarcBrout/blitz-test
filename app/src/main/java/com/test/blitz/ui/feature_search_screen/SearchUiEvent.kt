package com.test.blitz.ui.feature_search_screen

import com.test.blitz.core.mvi.UiEvent
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.User

sealed class SearchUiEvent : UiEvent {
    class Search(val query: String) : SearchUiEvent()
    class PhotosResult(val photos: List<Photo>) : SearchUiEvent()
    class UsersResult(val users: List<User>) : SearchUiEvent()
    class Error(val e: Throwable) : SearchUiEvent()
    object Clear : SearchUiEvent()

}