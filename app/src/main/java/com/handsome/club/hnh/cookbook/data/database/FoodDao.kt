package com.handsome.club.hnh.cookbook.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Dao
interface FoodDao {

    @Transaction
    suspend fun insertFood(food: FoodEntity) {
        val foodId = insertFoodInternal(food)

        bulkIngredientInsert(food.ingredients)
            .map { FoodIngredientCrossRef(foodId, it) }
            .apply(::bulkFoodIngredientCrossRefInsert)

        bulkFepInsert(food.feps)
            .map { FoodFepCrossRef(foodId, it) }
            .apply(::bulkFoodFepRefInsert)
    }

    // Food

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodInternal(food: FoodEntity): Long

    suspend fun getAllFoods(): Flow<List<FoodEntity>> = getAllFoodsWithIngredientsAndFeps().map { list -> list.map { it.food } }

    @Transaction
    @Query("SELECT * FROM foods")
    fun getAllFoodsWithIngredientsAndFeps(): Flow<List<FoodWithIngredientsAndFeps>>


    // Ingredient

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkFoodIngredientCrossRefInsert(foodIngredientRefs: List<FoodIngredientCrossRef>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkIngredientInsert(ingredients: List<IngredientEntity>): List<Long>

    // Fep

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkFoodFepRefInsert(foodFepRefs: List<FoodFepCrossRef>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkFepInsert(feps: List<FepEntity>): List<Long>

    // Util

    suspend fun isEmpty(): Boolean = getFirstFood() == null

    @Query("SELECT * FROM foods LIMIT 1")
    suspend fun getFirstFood(): FoodEntity?

}
