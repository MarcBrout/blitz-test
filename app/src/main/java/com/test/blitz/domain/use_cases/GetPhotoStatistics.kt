package com.test.blitz.domain.use_cases

import com.test.blitz.core.Resource
import com.test.blitz.domain.models.Statistics

interface GetPhotoStatistics {
    suspend operator fun invoke(): Resource<Statistics>
}