package com.handsome.club.hnh.cookbook.data.database

import com.handsome.club.hnh.cookbook.base.realm.createRealmId
import com.handsome.club.hnh.cookbook.model.fep.fromName
import com.handsome.club.hnh.cookbook.model.food.Fep
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.Ingredient
import io.realm.kotlin.ext.toRealmList


fun Food.toRealm(): RealmFood {
    val food = this

    return RealmFood().apply {
        id = food.id.createRealmId()
        name = food.name
        resourceName = food.resourceName
        hunger = food.hunger
        energy = food.energy
        ingredients = food.ingredients.map { it.toRealm(this) }.toRealmList()
        feps = food.feps.map { it.toRealm(this) }.toRealmList()
        isFavorite = food.isFavorite
    }
}

fun RealmFood.toModel(): Food {
    val realm = this

    return Food(
        id = realm.id.toHexString(),
        name = realm.name,
        resourceName = realm.resourceName,
        hunger = realm.hunger,
        energy = realm.energy,
        ingredients = realm.ingredients.map { it.toModel() },
        feps = realm.feps.map { it.toModel() },
        isFavorite = realm.isFavorite
    )
}


private fun Fep.toRealm(owner: RealmFood): RealmFep = RealmFep().also {
    it.type = type.toString()
    it.value = value
}

fun RealmFep.toModel(): Fep {
    val realm = this

    return Fep(
        type = fromName(realm.type),
        value = realm.value,
    )
}

fun Ingredient.toRealm(owner: RealmFood): RealmIngredient = RealmIngredient().also {
    it.name = name
    it.percentage = percentage
}

fun RealmIngredient.toModel(): Ingredient {
    val realm = this

    return Ingredient(
        name = realm.name,
        percentage = realm.percentage,
    )
}