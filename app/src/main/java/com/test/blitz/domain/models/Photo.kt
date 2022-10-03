package com.test.blitz.domain.models

data class Photo(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val description: String,
    val urls: List<PhotoUrls>,
)

sealed class PhotoUrls {
    data class Regular(val url: String) : PhotoUrls()
    data class Small(val url: String) : PhotoUrls()
    data class Thumb(val url: String) : PhotoUrls()
    data class Full(val url: String) : PhotoUrls()
    data class Raw(val url: String) : PhotoUrls()
}
