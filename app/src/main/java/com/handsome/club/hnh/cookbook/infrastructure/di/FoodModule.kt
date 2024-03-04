package com.handsome.club.hnh.cookbook.infrastructure.di

import com.handsome.club.hnh.cookbook.data.database.RealmFoodPersistance
import com.handsome.club.hnh.cookbook.data.network.HnhFoodFoodsSource
import com.handsome.club.hnh.cookbook.model.food.FoodPersistance
import com.handsome.club.hnh.cookbook.model.food.FoodsSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FoodModule {

//    @Provides
//    fun foodsSource(source: LocalJsonFoodsSource): FoodsSource = source

    @Provides
    fun foodsSource(source: HnhFoodFoodsSource): FoodsSource = source

    @Provides
    fun foodPersistance(persistance: RealmFoodPersistance): FoodPersistance = persistance

}