package com.handsome.club.hnh.cookbook.infrastructure.di

import com.handsome.club.hnh.cookbook.data.database.RealmFood
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class PersistanceModule {

    @Provides
    @Singleton
    fun realm() = RealmConfiguration
        .create(schema = setOf(RealmFood::class))
        .run(Realm::open)

}