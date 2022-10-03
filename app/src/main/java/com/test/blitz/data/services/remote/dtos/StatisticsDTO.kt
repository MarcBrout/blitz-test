package com.test.blitz.data.services.remote.dtos

import com.google.gson.annotations.SerializedName

class StatisticsDTO(
    @SerializedName("id") val id: String,
    @SerializedName("downloads") val downloads: Int,
    @SerializedName("views") val views: Int,
    @SerializedName("likes") val likes: Int,
)
