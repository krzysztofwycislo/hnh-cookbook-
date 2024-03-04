package com.handsome.club.hnh.cookbook.infrastructure.di

import android.content.Context
import android.content.res.Resources
import com.handsome.club.hnh.cookbook.data.local.FepTypeAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    fun provideResources(@ApplicationContext context: Context): Resources = context.resources

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(FepTypeAdapter)
        .add(KotlinJsonAdapterFactory())
        .build()

}