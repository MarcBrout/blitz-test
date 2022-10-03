package com.test.blitz.data.services.remote.retrofit

import com.test.blitz.data.services.remote.RemoteService
import com.test.blitz.data.services.remote.dtos.PhotoDTO
import com.test.blitz.data.services.remote.dtos.StatisticsDTO
import com.test.blitz.data.services.remote.dtos.UserDTO
import com.test.blitz.data.services.remote.retrofit.RetrofitService.Companion.apiKey
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface RetrofitService : RemoteService {

    @Headers("Authorization: Client-ID $apiKey")
    @GET("photos?page=1&per_page=20")
    override suspend fun getPhotos(): List<PhotoDTO>

    @Headers("Authorization: Client-ID $apiKey")
    @GET("users/{username}/photos")
    override suspend fun getUserPhotos(@Path("username") username: String): List<PhotoDTO>

    @Headers("Authorization: Client-ID $apiKey")
    @GET("photos/{id}/statistics")
    override suspend fun getPhotoStatistics(@Path("id") id: String): StatisticsDTO

    @Headers("Authorization: Client-ID $apiKey")
    @GET("photos/{id}")
    override suspend fun getPhoto(@Path("id") id: String): PhotoDTO

    @Headers("Authorization: Client-ID $apiKey")
    @GET("search/photos?query={search}&page=1&per_page=30")
    override suspend fun searchPhotos(@Path("search") search: String): List<PhotoDTO>

    @Headers("Authorization: Client-ID $apiKey")
    @GET("search/users?query={search}&page=1&per_page=30")
    override suspend fun searchUsers(@Path("search") search: String): List<UserDTO>

    companion object {
        //Todo: Move to gradle.properties, but for this test I will leave it here and remove it from git later
        private const val apiKey = "HPhY9e8OnJBLk4s5BRFQDPaXybX4qCmgojGxdLpGFv4"
    }
}