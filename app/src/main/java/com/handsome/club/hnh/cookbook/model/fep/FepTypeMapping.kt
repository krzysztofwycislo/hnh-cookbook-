package com.handsome.club.hnh.cookbook.model.fep

import com.handsome.club.hnh.cookbook.model.fep.FepType.Agility
import com.handsome.club.hnh.cookbook.model.fep.FepType.Charisma
import com.handsome.club.hnh.cookbook.model.fep.FepType.Constitution
import com.handsome.club.hnh.cookbook.model.fep.FepType.Dexterity
import com.handsome.club.hnh.cookbook.model.fep.FepType.Intelligence
import com.handsome.club.hnh.cookbook.model.fep.FepType.Perception
import com.handsome.club.hnh.cookbook.model.fep.FepType.Psyche
import com.handsome.club.hnh.cookbook.model.fep.FepType.Strength
import com.handsome.club.hnh.cookbook.model.fep.FepType.Will


fun fromApiName(fepName: String) = fepMapping.getValue(fepName)

val fepMapping = mapOf(
    Strength(1).toMapEntry(),
    Strength(2).toMapEntry(),

    Agility(1).toMapEntry(),
    Agility(2).toMapEntry(),

    Constitution(1).toMapEntry(),
    Constitution(2).toMapEntry(),

    Perception(1).toMapEntry(),
    Perception(2).toMapEntry(),

    Dexterity(1).toMapEntry(),
    Dexterity(2).toMapEntry(),

    Intelligence(1).toMapEntry(),
    Intelligence(2).toMapEntry(),

    Charisma(1).toMapEntry(),
    Charisma(2).toMapEntry(),

    Will(1).toMapEntry(),
    Will(2).toMapEntry(),

    Psyche(1).toMapEntry(),
    Psyche(2).toMapEntry(),
)

fun FepType.toMapEntry(): Pair<String, FepType> = toString() to this
