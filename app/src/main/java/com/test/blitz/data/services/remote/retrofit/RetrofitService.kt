package com.test.blitz.data.services.remote.retrofit

import com.test.blitz.data.services.remote.RemoteService
import com.test.blitz.data.services.remote.dtos.PhotoDTO
import com.test.blitz.data.services.remote.dtos.StatisticsDTO
import com.test.blitz.data.services.remote.dtos.UserDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface RetrofitService : RemoteService {
    @Headers("Authorization: Client-ID HPhY9e8OnJBLk4s5BRFQDPaXybX4qCmgojGxdLpGFv4")
    @GET("photos?page=1&per_page=20")
    override suspend fun getPhotos(): List<PhotoDTO>

    @Headers("Authorization: Client-ID HPhY9e8OnJBLk4s5BRFQDPaXybX4qCmgojGxdLpGFv4")
    @GET("users/{id}/photos")
    override suspend fun getUserPhotos(id: String): List<PhotoDTO>

    @Headers("Authorization: Client-ID HPhY9e8OnJBLk4s5BRFQDPaXybX4qCmgojGxdLpGFv4")
    @GET("photos/{id}/statistics")
    override suspend fun getPhotoStatistics(id: String): StatisticsDTO
}