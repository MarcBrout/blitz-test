package com.test.blitz.domain.repositories

import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.User

interface SearchRepository {
    suspend fun searchPhotos(search: String): List<Photo>
    suspend fun searchUsers(search: String): List<User>
}
