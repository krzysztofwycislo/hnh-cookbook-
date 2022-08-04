package com.handsome.club.hnh.cookbook.model.food

import com.handsome.club.hnh.cookbook.data.database.FoodEntity

interface FoodsSource {

    suspend fun fetchAllFoods(): List<FoodEntity>

}
