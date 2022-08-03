package com.handsome.club.hnh.cookbook.ui

import androidx.compose.ui.graphics.Color
import com.handsome.club.hnh.cookbook.model.food.Fep


val fepSignatureColors = mapOf(
    "Strength" to Color.Black,
    "Agility" to Color.DarkGray,
    "Constitution" to Color.Red,
    "Dexterity" to Color.Yellow,
    "Intelligence" to Color.Blue,
    "Charisma" to Color.LightGray,
    "Will" to Color.Magenta,
)

fun determineFepSignatureColors(fep: Fep): Color = fepSignatureColors
    .filter { fep.name.contains(it.key) }.values.first()