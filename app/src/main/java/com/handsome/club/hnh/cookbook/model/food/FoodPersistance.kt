package com.handsome.club.hnh.cookbook.model.food

interface FoodPersistance {

    fun isEmpty(): Boolean

    fun insertFoods(foods: Any)

}
