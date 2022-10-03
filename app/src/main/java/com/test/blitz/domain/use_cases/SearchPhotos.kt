package com.test.blitz.domain.use_cases

import com.test.blitz.core.Resource
import com.test.blitz.domain.common.safeResource
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.repositories.SearchRepository

interface SearchPhotos {
    suspend operator fun invoke(
        search: String,
    ): Resource<List<Photo>>
}

class SearchPhotosImpl(
    private val repository: SearchRepository,
) : SearchPhotos {
    override suspend fun invoke(search: String): Resource<List<Photo>> = safeResource {
        repository.searchPhotos(search)
    }
}