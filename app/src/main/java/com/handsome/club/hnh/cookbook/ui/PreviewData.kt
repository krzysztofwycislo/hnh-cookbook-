package com.handsome.club.hnh.cookbook.ui

import androidx.compose.ui.graphics.Color
import com.handsome.club.hnh.cookbook.model.food.Fep
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.Ingredient
import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.random.Random


val fepSignatureColors = mapOf(
    "Strength" to Color.Black,
    "Agility" to Color.DarkGray,
    "Constitution" to Color.Red,
    "Perception" to Color.Black,
    "Dexterity" to Color.Yellow,
    "Intelligence" to Color.Blue,
    "Charisma" to Color.LightGray,
    "Will" to Color.Magenta,
)

fun determineFepSignatureColors(fep: Fep): Color = fepSignatureColors
    .filter { fep.name.contains(it.key) }.values.first()

val exampleFoods = listOf(
    createExampleFood(1),
    createExampleFood(2),
    createExampleFood(3),
    createExampleFood(4),
)

fun createExampleFood(seed: Int): Food {
    val ingredients = IntStream.rangeClosed(1, seed)
        .mapToObj { Ingredient("ingredient$seed-$it", 100) }
        .collect(Collectors.toList())

    val feps = IntStream.rangeClosed(1, seed)
        .mapToObj { Fep(randomRealFepName(), it.toFloat()) }
        .collect(Collectors.toList())

    return Food("food$seed", "resource$seed", seed.toFloat(), seed * 200, ingredients, feps)
}

private fun randomRealFepName(): String {
    val fepKey = fepSignatureColors.keys.toList()[Random.nextInt(fepSignatureColors.size)]
    return if (Random.nextBoolean()) {
        "$fepKey +2"
    } else {
        "$fepKey +1"
    }
}