package com.example.water_drinking_whale.di

import com.example.water_drinking_whale.data.main.api.MainApi
import com.example.water_drinking_whale.data.user.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideMainApi(retrofit: Retrofit): MainApi = retrofit.create(MainApi::class.java)

    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)
}
