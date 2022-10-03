package com.test.blitz.data.mappers

import com.test.blitz.data.services.remote.dtos.UrlsDTO
import com.test.blitz.domain.models.PhotoUrls

fun UrlsDTO.toPhotoUrls() = mapOf(
    PhotoUrls.Raw to raw,
    PhotoUrls.Full to full,
    PhotoUrls.Regular to regular,
    PhotoUrls.Small to small,
    PhotoUrls.Thumb to thumb,
)