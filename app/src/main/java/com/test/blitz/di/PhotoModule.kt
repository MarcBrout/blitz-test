package com.test.blitz.di

import com.test.blitz.data.repositories.PhotoRepositoryImpl
import com.test.blitz.domain.repositories.PhotoRepository
import com.test.blitz.domain.use_cases.GetPhotoStatistics
import com.test.blitz.domain.use_cases.GetPhotoStatisticsImpl
import com.test.blitz.domain.use_cases.GetPhotos
import com.test.blitz.domain.use_cases.GetPhotosImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PhotoModule {
    @Provides
    @Singleton
    fun providePhotoRepository(): PhotoRepository {
        return PhotoRepositoryImpl()
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
}