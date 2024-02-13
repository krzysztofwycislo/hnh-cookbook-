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
    "Strength +1" to Strength(1),
    "Strength +2" to Strength(2),

    "Agility +1" to Agility(1),
    "Agility +2" to Agility(2),

    "Constitution +1" to Constitution(1),
    "Constitution +2" to Constitution(2),

    "Perception +1" to Perception(1),
    "Perception +2" to Perception(2),

    "Dexterity +1" to Dexterity(1),
    "Dexterity +2" to Dexterity(2),

    "Intelligence +1" to Intelligence(1),
    "Intelligence +2" to Intelligence(2),

    "Charisma +1" to Charisma(1),
    "Charisma +2" to Charisma(2),

    "Will +1" to Will(1),
    "Will +2" to Will(2),

    "Psyche +1" to Psyche(1),
    "Psyche +2" to Psyche(2),
)