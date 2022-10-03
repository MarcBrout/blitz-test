package com.test.blitz.domain.models

data class Photo(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val description: String,
    val user: User,
    val urls: Map<PhotoUrls, String>,
)

enum class PhotoUrls {
    Regular,
    Small,
    Thumb,
    Full,
    Raw,
}
