package com.handsome.club.hnh.cookbook.model.food

import com.handsome.club.hnh.cookbook.model.fep.Fep
import com.squareup.moshi.Json

data class Food(
    @Json(ignore = true) val id: Long = 0,
    val itemName: String,
    val resourceName: String,
    val hunger: Float,
    val energy: Int,
    val ingredients: List<Ingredient>,
    val feps: List<Fep>,
    val isFavorite: Boolean
) {

    val sortedFeps = feps.sortedBy { -it.value }

}

data class Ingredient(
    @Json(ignore = true) val id: Long = 0,
    val name: String,
    val percentage: Int
)