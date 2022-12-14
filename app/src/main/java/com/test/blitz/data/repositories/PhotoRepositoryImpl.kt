package com.test.blitz.data.repositories

import com.test.blitz.data.mappers.toPhoto
import com.test.blitz.data.mappers.toStatistics
import com.test.blitz.data.services.remote.RemoteService
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.Statistics
import com.test.blitz.domain.repositories.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val remoteService: RemoteService
): PhotoRepository {
    override suspend fun getPhoto(id: String): Photo {
        return remoteService.getPhoto(id).toPhoto()
    }

    override suspend fun getPhotos(): List<Photo> {
        return remoteService.getPhotos().map { it.toPhoto() }
    }

    override suspend fun getUserPhotos(username: String): List<Photo> {
        return remoteService.getUserPhotos(username).map { it.toPhoto() }
    }

    override suspend fun getPhotoStatistics(id: String): Statistics {
        return remoteService.getPhotoStatistics(id).toStatistics()
    }
}