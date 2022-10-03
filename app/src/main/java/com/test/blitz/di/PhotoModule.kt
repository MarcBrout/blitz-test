package com.test.blitz.di

import com.test.blitz.data.repositories.PhotoRepositoryImpl
import com.test.blitz.data.services.remote.RemoteService
import com.test.blitz.domain.repositories.PhotoRepository
import com.test.blitz.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.http.POST
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PhotoModule {
    @Provides
    @Singleton
    fun providePhotoRepository(
        remoteService: RemoteService
    ): PhotoRepository {
        return PhotoRepositoryImpl(remoteService)
    }

    @Provides
    @Singleton
    fun provideGetPhoto(
        repository: PhotoRepository
    ): GetPhoto {
        return GetPhotoImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetPhotos(
        repository: PhotoRepository
    ): GetPhotos {
        return GetPhotosImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetPhotoStatistics(
        repository: PhotoRepository
    ): GetPhotoStatistics {
        return GetPhotoStatisticsImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetUserPhotos(
        repository: PhotoRepository
    ): GetUserPhotos {
        return GetUserPhotosImpl(repository)
    }
}