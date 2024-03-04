package com.handsome.club.hnh.cookbook.data.database

import com.handsome.club.hnh.cookbook.model.food.Fep
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.Ingredient
import io.realm.kotlin.ext.toRealmList
import org.mongodb.kbson.ObjectId


fun Food.toRealm(): RealmFood {
    val food = this

    return RealmFood().apply {
        id = ObjectId(food.id)
        name = food.name
        resourceName = food.resourceName
        hunger = food.hunger
        energy = food.energy
        ingredients = food.ingredients.map { it.toRealm(this) }.toRealmList()
        feps = food.feps.map { it.toRealm(this) }.toRealmList()
        isFavorite = food.isFavorite
    }
}

private fun Fep.toRealm(owner: RealmFood): RealmFep = RealmFep().also {
    it.type = type.toString()
    it.value = value
}

fun Ingredient.toRealm(owner: RealmFood): RealmIngredient = RealmIngredient().also {
    it.name = name
    it.percentage = percentage
}