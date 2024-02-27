package com.handsome.club.hnh.cookbook.model.food

interface FoodPersistance {

    suspend fun isEmpty(): Boolean

    suspend fun insertFoods(foods: List<Food>)

}
