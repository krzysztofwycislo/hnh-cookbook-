package com.handsome.club.hnh.cookbook.data.database

import androidx.room.*
import com.handsome.club.hnh.cookbook.utils.forEachApply
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Dao
interface FoodDao {

    // Food

    @Transaction
    suspend fun insertFood(food: FoodEntity) {
        val foodId = insertFoodInternal(food)

        food.ingredients
            .forEachApply { it.foodId = foodId }
            .let { bulkIngredientInsert(it) }

        food.feps
            .forEachApply { it.foodId = foodId }
            .let { bulkFepInsert(it) }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodInternal(food: FoodEntity): Long

    suspend fun getAllFoods(): Flow<List<FoodEntity>> = getAllFoodsWithIngredientsAndFeps().map { list -> list.map { it.food } }

    @Transaction
    @Query("SELECT * FROM foods")
    fun getAllFoodsWithIngredientsAndFeps(): Flow<List<FoodWithIngredientsAndFeps>>


    // Ingredient

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkIngredientInsert(ingredients: List<IngredientEntity>): List<Long>

    // Fep

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkFepInsert(feps: List<FepEntity>): List<Long>

    // Util

    suspend fun isEmpty(): Boolean = getFirstFood() == null

    @Query("SELECT * FROM foods LIMIT 1")
    suspend fun getFirstFood(): FoodEntity?

}
