package com.test.blitz.data.services.remote.retrofit

import com.test.blitz.data.services.remote.RemoteService
import com.test.blitz.data.services.remote.dtos.PhotoDTO
import com.test.blitz.data.services.remote.dtos.StatisticsDTO
import com.test.blitz.data.services.remote.dtos.UserDTO
import retrofit2.http.GET

interface RetrofitService : RemoteService {
    @GET("photos")
    override suspend fun getPhotos(): List<PhotoDTO>

    @GET("users/{id}/photos")
    override suspend fun getUserPhotos(id: String): List<PhotoDTO>

    @GET("photos/{id}/statistics")
    override suspend fun getPhotoStatistics(id: String): StatisticsDTO
}