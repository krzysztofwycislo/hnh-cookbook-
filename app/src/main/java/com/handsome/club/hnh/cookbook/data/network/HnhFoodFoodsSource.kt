package com.handsome.club.hnh.cookbook.data.network

import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.FoodsSource
import retrofit2.http.GET


interface HnhFoodFoodsSource : FoodsSource {

    @GET("data/food-info2.json")
    override suspend fun fetchAllFoods(): List<Food>

}