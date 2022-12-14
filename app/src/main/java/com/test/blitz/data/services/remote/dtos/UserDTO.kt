package com.test.blitz.data.services.remote.dtos

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("id") val id: String,
    @SerializedName("username") val username: String,
    @SerializedName("name") val name: String,
    @SerializedName("bio") val bio: String?,
    @SerializedName("profile_image") val profileImage: ProfileImageSizesDTO,
)