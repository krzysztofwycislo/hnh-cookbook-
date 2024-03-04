package com.handsome.club.hnh.cookbook.model.food

import android.util.Range
import com.handsome.club.hnh.cookbook.model.fep.FepFilters

data class FoodFilters(
    val name: String,
    val hunger: Range<Float>,
    val energy: Range<Int>,
    val includedIngredients: List<Ingredient>,
    val excludedIngredients: List<Ingredient>,
    val feps: List<FepFilters>,
) {

    companion object {

        val DEFAULT = FoodFilters(
            name = "",
            hunger = Range.create(Float.MIN_VALUE, Float.MAX_VALUE),
            energy = Range.create(Int.MIN_VALUE, Int.MAX_VALUE),
            includedIngredients = emptyList(),
            excludedIngredients = emptyList(),
            feps = emptyList()
        )

    }
}