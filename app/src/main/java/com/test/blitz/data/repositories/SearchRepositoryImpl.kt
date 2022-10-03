package com.test.blitz.data.repositories

import com.test.blitz.data.mappers.toPhoto
import com.test.blitz.data.mappers.toUser
import com.test.blitz.data.services.remote.RemoteService
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.User
import com.test.blitz.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteService: RemoteService,
): SearchRepository {
    override suspend fun searchPhotos(search: String): List<Photo> {
        return remoteService.searchPhotos(search).map { it.toPhoto() }
    }

    override suspend fun searchUsers(search: String): List<User> {
        return remoteService.searchUsers(search).map { it.toUser() }
    }
}