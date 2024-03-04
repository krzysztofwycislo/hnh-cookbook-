package com.handsome.club.hnh.cookbook.model.food

import com.handsome.club.hnh.cookbook.base.paging.Pagination

interface FoodPersistance {

    suspend fun isEmpty(): Boolean

    suspend fun populateFoods(foods: List<Food>)

    suspend fun getFoods(paging: Pagination, filters: FoodFilters): List<Food>

}
