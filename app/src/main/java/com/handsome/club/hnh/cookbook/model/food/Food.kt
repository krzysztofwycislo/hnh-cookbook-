package com.handsome.club.hnh.cookbook.model.food

import com.handsome.club.hnh.cookbook.model.fep.FepType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Food(

    @Json(ignore = true)
    val id: String = "",

    @Json(name = "itemName")
    val name: String,

    val resourceName: String,
    val hunger: Float,
    val energy: Int,
    val ingredients: List<Ingredient>,
    val feps: List<Fep>,
    val isFavorite: Boolean = false
) {

    val sortedFeps = feps.sortedBy { -it.value }

}

@JsonClass(generateAdapter = true)
data class Ingredient(
    val name: String,
    val percentage: Int
)

@JsonClass(generateAdapter = true)
data class Fep(
    @Json(name = "name")
    val type: FepType,
    val value: Float,
)