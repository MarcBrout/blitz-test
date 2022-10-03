package com.test.blitz.data.mappers

import com.test.blitz.data.services.remote.dtos.PhotoDTO
import com.test.blitz.domain.models.Photo

fun PhotoDTO.toPhoto() = Photo(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    description = description,
    user = user.toUser(),
    urls = urls.toPhotoUrls(),
    likes = likes,
)
