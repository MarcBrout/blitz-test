package com.test.blitz.data.services.remote

import com.test.blitz.data.services.remote.dtos.PhotoDTO
import com.test.blitz.data.services.remote.dtos.StatisticsDTO
import com.test.blitz.data.services.remote.dtos.UserDTO

interface RemoteService {
    suspend fun getPhotos(): List<PhotoDTO>
    suspend fun getUserPhotos(username: String): List<PhotoDTO>
    suspend fun getPhotoStatistics(id: String): StatisticsDTO
    suspend fun getPhoto(id: String): PhotoDTO
}