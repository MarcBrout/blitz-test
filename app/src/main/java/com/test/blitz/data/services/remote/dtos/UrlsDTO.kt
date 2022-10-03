package com.test.blitz.data.services.remote.dtos

import com.google.gson.annotations.SerializedName

data class UrlsDTO(
    @SerializedName("raw") val raw: String,
    @SerializedName("full") val full: String,
    @SerializedName("regular") val regular: String,
    @SerializedName("small") val small: String,
    @SerializedName("thumb") val thumb: String,
)