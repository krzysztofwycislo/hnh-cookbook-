package com.handsome.club.hnh.cookbook.ui

import androidx.compose.ui.graphics.Color
import com.handsome.club.hnh.cookbook.model.food.Fep
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.Ingredient
import kotlin.random.Random


class FepTypeData(val shortName: String, val color: Color)

val fepSignatureColors = mapOf(
    "Strength +1" to FepTypeData("str", Color(0xFFBF9794)),
    "Strength +2" to FepTypeData("str2", Color(0xFFBF9794)),

    "Agility +1" to FepTypeData("agi", Color(0xFF9991DC)),
    "Agility +2" to FepTypeData("agi2", Color(0xFF9991DC)),

    "Constitution +1" to FepTypeData("con", Color(0xFFc29ab4)),
    "Constitution +2" to FepTypeData("con2", Color(0xFFc29ab4)),

    "Perception +1" to FepTypeData("prc", Color(0xFFE4BF98)),
    "Perception +2" to FepTypeData("prc2", Color(0xFFE4BF98)),

    "Dexterity +1" to FepTypeData("dex", Color(0xFFFEFDCC)),
    "Dexterity +2" to FepTypeData("dex2", Color(0xFFFEFDCC)),

    "Intelligence +1" to FepTypeData("int", Color(0xFF9DB7B9)),
    "Intelligence +2" to FepTypeData("int2", Color(0xFF9DB7B9)),

    "Charisma +1" to FepTypeData("csm", Color(0xFF9BEEB1)),
    "Charisma +2" to FepTypeData("csm2", Color(0xFF9BEEB1)),

    "Will +1" to FepTypeData("wil", Color(0xFFE4F38F)),
    "Will +2" to FepTypeData("wil2", Color(0xFFE4F38F)),

    "Psyche +1" to FepTypeData("psy", Color(0xFFC48DFD)),
    "Psyche +2" to FepTypeData("psy2", Color(0xFFC48DFD)),
)

fun determineFepSignatureColors(fep: Fep): FepTypeData = fepSignatureColors.getValue(fep.name)

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
        Fep(initialId, randomRealFepName(), 3.1F),
        Fep(initialId + 1, randomRealFepName(), 22F),
        Fep(initialId + 2, randomRealFepName(), 120.5F),
        Fep(initialId + 3, randomRealFepName(), 0F)
    )

    return Food(seed.toLong(), "Food Example $seed", "resource$seed", seed.toFloat(), seed * 200, ingredients, feps)
}

private fun randomRealFepName(): String {
    return fepSignatureColors.keys.toList()[Random.nextInt(fepSignatureColors.size)]
}