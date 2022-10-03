package com.test.blitz.data.mappers

import com.test.blitz.data.services.remote.dtos.UserDTO
import com.test.blitz.domain.models.User

fun UserDTO.toUser() = User(
    id = id,
    username = username,
    name = name,
    bio = bio,
)