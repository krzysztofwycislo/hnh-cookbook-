package com.handsome.club.hnh.cookbook.data.database.food

import com.handsome.club.hnh.cookbook.model.fep.Fep
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.Ingredient
import javax.inject.Inject


class FoodMapper @Inject constructor() {

    fun toEntity(food: Food) = with(food) {
        FoodEntity(itemName, resourceName, hunger, energy)
            .also {
                it.ingredients = ingredients.toIngredientEntities()
                it.feps = feps.toFepEntities()
            }
    }

    fun toModels(entities: List<FoodEntity>) = entities.map {
        with(it) {
            Food(
                foodId,
                itemName,
                resourceName,
                hunger,
                energy,
                ingredients.toIngredientModels(),
                feps.toFepModels()
            )
        }
    }


    private fun List<Ingredient>.toIngredientEntities() = map { it.toEntity() }
    private fun Ingredient.toEntity() = IngredientEntity(name, percentage)

    private fun List<IngredientEntity>.toIngredientModels() = map { it.toModel() }
    private fun IngredientEntity.toModel() = Ingredient(ingredientId, name, percentage)


    private fun List<Fep>.toFepEntities() = map { it.toEntity() }
    private fun Fep.toEntity() = FepEntity(name, value)

    private fun List<FepEntity>.toFepModels() = map { it.toFepModel() }
    private fun FepEntity.toFepModel() = Fep(fepId, name, value)

}

