package com.handsome.club.hnh.cookbook.infrastructure.di

import com.handsome.club.hnh.cookbook.data.network.HnhFoodFoodsSource
import com.handsome.club.hnh.cookbook.model.food.FoodsSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FoodModule {

//    @Provides
//    fun provideFoodsSource(source: LocalJsonFoodsSource): FoodsSource = source

    @Provides
    fun provideFoodsSource(source: HnhFoodFoodsSource): FoodsSource = source

}