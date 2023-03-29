package com.handsome.club.hnh.cookbook.ui

import com.handsome.club.hnh.cookbook.model.fep.Fep
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.Ingredient

object FoodMocks {

    val exampleFoods = listOf(
        createExampleFood(1),
        createExampleFood(2),
        createExampleFood(3),
        createExampleFood(4),
    )

    fun createExampleFood(seed: Int): Food {
        val initialId = seed * 100L

        val ingredients = listOf(
            Ingredient(initialId, "Turnip", 10),
            Ingredient(initialId + 1, "Poppy", 100),
            Ingredient(initialId + 2, "Piece of slave", 70),
            Ingredient(initialId + 3, "Bear Meat", 1)
        )

        val feps = listOf(
            Fep(initialId, "Strength +1", 3.1F),
            Fep(initialId + 1, "Strength +2", 3.1F),
            Fep(initialId + 2, "Agility +1", 22F),
            Fep(initialId + 3, "Will +2", 120.5F),
            Fep(initialId + 4, "Psyche +1", 0F)
        )

        return Food(seed.toLong(), "Food Example $seed", "resource$seed", seed.toFloat(), seed * 200, ingredients, feps)
    }

    val exampleFeps = exampleFoods.first().feps

}