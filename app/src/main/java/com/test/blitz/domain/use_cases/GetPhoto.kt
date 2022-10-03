package com.test.blitz.domain.use_cases

import com.test.blitz.core.Resource
import com.test.blitz.domain.common.safeResource
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.repositories.PhotoRepository
import javax.inject.Inject

interface GetPhoto {
    suspend operator fun invoke(id: String): Resource<Photo>
}

class GetPhotoImpl @Inject constructor(
    private val repository: PhotoRepository
): GetPhoto {
    override suspend fun invoke(id: String): Resource<Photo> = safeResource {
        repository.getPhoto(id)
    }
}