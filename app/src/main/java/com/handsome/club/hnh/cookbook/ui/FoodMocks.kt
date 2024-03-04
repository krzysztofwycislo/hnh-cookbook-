package com.handsome.club.hnh.cookbook.ui

import com.handsome.club.hnh.cookbook.model.fep.FepType
import com.handsome.club.hnh.cookbook.model.food.Fep
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
        val ingredients = listOf(
            Ingredient("Turnip", 10),
            Ingredient("Poppy", 100),
            Ingredient("Piece of slave", 70),
            Ingredient("Bear Meat", 1)
        )

        val feps = listOf(
            Fep(FepType.Strength(1), 3.1F),
            Fep(FepType.Strength(2), 3.1F),
            Fep(FepType.Agility(1), 22F),
            Fep(FepType.Will(2), 120.5F),
            Fep(FepType.Psyche(1), 0F)
        )

        return Food(seed.toString(), "Food Example $seed", "resource$seed", seed.toFloat(), seed * 200, ingredients, feps, seed % 2 == 0)
    }

    val exampleFeps = exampleFoods.first().feps

}