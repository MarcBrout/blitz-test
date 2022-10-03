package com.test.blitz.di

import com.test.blitz.data.services.remote.RemoteService
import com.test.blitz.data.services.remote.retrofit.RetrofitService
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


class RemoteServiceModule {
    @Provides
    @Singleton
    fun provideRemoteService(): RemoteService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RetrofitService::class.java)
    }
}