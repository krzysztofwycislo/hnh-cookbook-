package com.handsome.club.hnh.cookbook.infrastructure.di

import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import com.handsome.club.hnh.cookbook.data.database.MainDatabase
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
    fun provideMainDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MainDatabase::class.java, "main-database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideResources(@ApplicationContext context: Context): Resources = context.resources

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

}