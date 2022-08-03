package com.handsome.club.hnh.cookbook.model.food

data class Food(
    val itemName: String,
    val resourceName: String,
    val hunger: Float,
    val energy: Int,
    val ingredients: List<Ingredient>,
    val feps: List<Fep>,
)

data class Ingredient(
    val name: String,
    val percentage: Int
)

data class Fep(
    val name: String,
    val value: Float
)
