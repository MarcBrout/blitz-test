package com.test.blitz.data.services.remote.dtos

import com.google.gson.annotations.SerializedName

data class ProfileImageSizesDTO(
    @SerializedName("small") val small: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("large") val large: String,
)