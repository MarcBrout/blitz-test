package com.test.blitz.domain.repositories

import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.Statistics
import com.test.blitz.domain.models.User

interface PhotoRepository {
    suspend fun getPhotos(): List<Photo>
    suspend fun getUserPhotos(id: String): List<Photo>
    suspend fun getPhotoStatistics(id: String): Statistics
}