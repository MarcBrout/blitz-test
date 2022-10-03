package com.test.blitz.domain.use_cases

import com.test.blitz.core.Resource
import com.test.blitz.domain.common.safeResource
import com.test.blitz.domain.models.User
import com.test.blitz.domain.repositories.SearchRepository

interface SearchUsers {
    suspend operator fun invoke(search: String) : Resource<List<User>>
}

class SearchUsersImpl(
    private val repository: SearchRepository,
) : SearchUsers {
    override suspend fun invoke(search: String): Resource<List<User>> = safeResource {
        repository.searchUsers(search)
    }
}