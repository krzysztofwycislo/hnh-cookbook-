package com.handsome.club.hnh.cookbook.data.database

import com.handsome.club.hnh.cookbook.model.food.Fep
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.Ingredient
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class RealmFood(
    @PrimaryKey
    var id: ObjectId = ObjectId(),
    val name: String = "",
    val resourceName: String = "",
    val hunger: Float = 0f,
    val energy: Int = 0,
    val ingredients: RealmList<RealmIngredient> = realmListOf(),
    val feps: RealmList<RealmFep> = realmListOf(),
    val isFavorite: Boolean = false
) : RealmObject {

    constructor(food: Food) : this(
        id = ObjectId(food.id),
        name = food.name,
        resourceName = food.resourceName,
        hunger = food.hunger,
        energy = food.energy,
        ingredients = realmListOf(
            *(food.ingredients.map(::RealmIngredient).toTypedArray())
        ),
        feps = realmListOf(
            *(food.feps.map(::RealmFep).toTypedArray())
        ),
        isFavorite = food.isFavorite,
    )

}

data class RealmIngredient(
    val name: String = "",
    val percentage: Int = 0
) : EmbeddedRealmObject {

    constructor(ingredient: Ingredient) : this(
        name = ingredient.name,
        percentage = ingredient.percentage,
    )

}

data class RealmFep(
    val type: String = "",
    val value: Float = 0f,
) : EmbeddedRealmObject {

    constructor(fep: Fep) : this(
        type = fep.type.toString(),
        value = fep.value,
    )

}