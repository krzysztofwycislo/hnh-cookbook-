package com.handsome.club.hnh.cookbook.infrastructure.di

import com.handsome.club.hnh.cookbook.data.network.HnhFoodFoodsSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://food.hearthlands.net/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

    @Provides
    @Singleton
    fun hnhFoodApi(retrofit: Retrofit): HnhFoodFoodsSource = retrofit.create()

}