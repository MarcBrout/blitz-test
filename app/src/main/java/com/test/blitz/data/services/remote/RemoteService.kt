package com.test.blitz.data.services.remote

import com.test.blitz.data.services.remote.dtos.*

interface RemoteService {
    suspend fun getPhotos(): List<PhotoDTO>
    suspend fun getUserPhotos(username: String): List<PhotoDTO>
    suspend fun getPhotoStatistics(id: String): StatisticsDTO
    suspend fun getPhoto(id: String): PhotoDTO
    suspend fun searchPhotos(search: String, page: Int = 1, per_page: Int = 30): SearchPhotosDTO
    suspend fun searchUsers(search: String, page: Int = 1, per_page: Int = 30): SearchUsersDTO
}