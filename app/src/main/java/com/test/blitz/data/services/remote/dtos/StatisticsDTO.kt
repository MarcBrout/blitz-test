package com.test.blitz.data.services.remote.dtos

import com.google.gson.annotations.SerializedName

class StatisticsDTO(
    @SerializedName("id") val id: String,
    @SerializedName("downloads") val downloads: ViewsDTO,
    @SerializedName("views") val views: DownloadsDTO,
    @SerializedName("likes") val likes: LikesDTO
)

class ViewsDTO(
    @SerializedName("total") val total: Int,
)

class DownloadsDTO(
    @SerializedName("total") val total: Int,
)

class LikesDTO(
    @SerializedName("total") val total: Int,
)