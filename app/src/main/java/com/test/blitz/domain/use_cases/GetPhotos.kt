package com.test.blitz.domain.use_cases

import com.test.blitz.core.Resource
import com.test.blitz.domain.models.Photo

interface GetPhotos {
    suspend operator fun invoke(): Resource<List<Photo>>
}