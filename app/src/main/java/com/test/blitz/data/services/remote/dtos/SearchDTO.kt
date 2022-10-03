package com.test.blitz.data.services.remote.dtos

import com.google.gson.annotations.SerializedName

data class SearchPhotosDTO(
    @SerializedName("results") val results: List<PhotoDTO>,
)

data class SearchUsersDTO(
    @SerializedName("results") val results: List<UserDTO>,
)