package com.handsome.club.hnh.cookbook.model.food

interface FoodsSource {

    suspend fun fetchAllFoods(): List<Food>

}
