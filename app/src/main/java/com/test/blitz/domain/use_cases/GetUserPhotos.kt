package com.test.blitz.domain.use_cases

import com.test.blitz.core.Resource
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.User

interface GetUserPhotos {
    suspend operator fun invoke(): Resource<Pair<User, List<Photo>>>
}