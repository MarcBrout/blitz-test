package com.test.blitz.domain.use_cases

import com.test.blitz.core.Resource
import com.test.blitz.domain.common.safeResource
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.repositories.PhotoRepository
import javax.inject.Inject

interface GetPhotos {
    suspend operator fun invoke(): Resource<List<Photo>>
}

class GetPhotosImpl @Inject constructor(
    private val repository: PhotoRepository
): GetPhotos {
    override suspend fun invoke(): Resource<List<Photo>> = safeResource {
        repository.getPhotos()
    }
}