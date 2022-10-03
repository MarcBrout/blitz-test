package com.test.blitz.domain.models

data class User(
    val id: String,
    val username: String,
    val name: String,
    val bio: String?,
    val profileImage: Map<ProfileImageSize, String>,
)

enum class ProfileImageSize {
    Small,
    Medium,
    Large,
}