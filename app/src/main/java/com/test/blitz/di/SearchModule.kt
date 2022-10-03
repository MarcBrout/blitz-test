package com.test.blitz.di

import com.test.blitz.data.repositories.SearchRepositoryImpl
import com.test.blitz.data.services.remote.RemoteService
import com.test.blitz.domain.repositories.SearchRepository
import com.test.blitz.domain.use_cases.SearchPhotos
import com.test.blitz.domain.use_cases.SearchPhotosImpl
import com.test.blitz.domain.use_cases.SearchUsers
import com.test.blitz.domain.use_cases.SearchUsersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Provides
    @Singleton
    fun provideRepository(remoteService: RemoteService): SearchRepository {
        return SearchRepositoryImpl(remoteService)
    }

    @Provides
    @Singleton
    fun provideSearchPhotos(
        repository: SearchRepository,
    ): SearchPhotos {
        return SearchPhotosImpl(repository)
    }

    @Provides
    @Singleton
    fun provideSearchUsers(
        repository: SearchRepository,
    ): SearchUsers {
        return SearchUsersImpl(repository)
    }
}