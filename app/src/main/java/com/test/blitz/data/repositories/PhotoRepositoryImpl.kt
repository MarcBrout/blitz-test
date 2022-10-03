package com.test.blitz.data.repositories

import com.test.blitz.data.services.remote.RemoteService
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.Statistics
import com.test.blitz.domain.models.User
import com.test.blitz.domain.repositories.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val remoteService: RemoteService
): PhotoRepository {
    override suspend fun getPhotos(): List<Photo> {
        TODO("Not yet implemented
    }

    override suspend fun getUserPhotos(id: String): Pair<User, List<Photo>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPhotoStatistics(id: String): Statistics {
        TODO("Not yet implemented")
    }
}