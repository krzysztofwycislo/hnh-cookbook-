package com.handsome.club.hnh.cookbook.data.food

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Dao
interface FoodDao {

    @Transaction
    suspend fun insertFood(food: FoodEntity) {
        insertFoodInternal(food)
        bulkIngredientInsert(food.ingredients)
        bulkFepInsert(food.feps)
    }

    // Food

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodInternal(food: FoodEntity)

    fun getAllFoods(): Flow<List<FoodEntity>> = getAllFoodsWithIngredientsAndFeps().map { list -> list.map { it.food } }

    @Transaction
    @Query("SELECT * FROM foods")
    fun getAllFoodsWithIngredientsAndFeps(): Flow<List<FoodWithIngredientsAndFeps>>


    // Ingredient

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: IngredientEntity)

    @Transaction
    suspend fun bulkIngredientInsert(ingredients: List<IngredientEntity>) {
        ingredients.forEach { ingredient -> insertIngredient(ingredient) }
    }

    // Fep

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFep(fep: FepEntity)

    @Transaction
    suspend fun bulkFepInsert(feps: List<FepEntity>) {
        feps.forEach { fep -> insertFep(fep) }
    }

    // Util

    suspend fun isEmpty(): Boolean = getFirstFood() == null

    @Query("SELECT * FROM foods LIMIT 1")
    suspend fun getFirstFood(): FoodEntity?

}
