package com.handsome.club.hnh.cookbook.infrastructure

import com.handsome.club.hnh.cookbook.model.food.FoodFilters

sealed class AppDestination(val destinationId: Int) {

    class FoodFilterScreen(
        val onResult: (FoodFilters) -> Unit
    ) : AppDestination(
        destinationId = 1
    )

}
