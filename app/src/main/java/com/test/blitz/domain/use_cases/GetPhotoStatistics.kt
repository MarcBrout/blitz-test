package com.test.blitz.domain.use_cases

import com.test.blitz.core.Resource
import com.test.blitz.domain.common.safeResource
import com.test.blitz.domain.models.Statistics
import com.test.blitz.domain.repositories.PhotoRepository
import javax.inject.Inject

interface GetPhotoStatistics {
    suspend operator fun invoke(id: String): Resource<Statistics>
}

class GetPhotoStatisticsImpl @Inject constructor(
    private val repository: PhotoRepository
): GetPhotoStatistics {
    override suspend fun invoke(id: String): Resource<Statistics> = safeResource {
        repository.getPhotoStatistics(id)
    }
}