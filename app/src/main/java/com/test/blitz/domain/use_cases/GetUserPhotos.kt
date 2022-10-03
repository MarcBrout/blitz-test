package com.test.blitz.domain.use_cases

import com.test.blitz.core.Resource
import com.test.blitz.domain.common.safeResource
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.User
import com.test.blitz.domain.repositories.PhotoRepository
import javax.inject.Inject

interface GetUserPhotos {
    suspend operator fun invoke(id: String): Resource<List<Photo>>
}

class GetUserPhotosImpl @Inject constructor(
    private val repository: PhotoRepository
): GetUserPhotos {
    override suspend fun invoke(id: String): Resource<List<Photo>> = safeResource {
        repository.getUserPhotos(id)
    }
}