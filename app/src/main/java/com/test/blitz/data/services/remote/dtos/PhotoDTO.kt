package com.test.blitz.data.services.remote.dtos

import com.google.gson.annotations.SerializedName

data class PhotoDTO (
    @SerializedName("id") val id: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("description") val description: String,
    @SerializedName("user") val user: UserDTO,
    @SerializedName("urls") val urls: UrlsDTO,
    @SerializedName("likes") val likes: Int,
)