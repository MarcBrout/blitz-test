package com.test.blitz.data.mappers

import com.test.blitz.data.services.remote.dtos.ProfileImageSizesDTO
import com.test.blitz.domain.models.ProfileImageSize

fun ProfileImageSizesDTO.toProfileImage() = mapOf(
    ProfileImageSize.Small to small,
    ProfileImageSize.Medium to medium,
    ProfileImageSize.Large to large,
)