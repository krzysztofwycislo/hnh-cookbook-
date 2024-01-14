package com.handsome.club.hnh.cookbook.data.database.food

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.handsome.club.hnh.cookbook.base.paging.Pagination
import com.handsome.club.hnh.cookbook.utils.forEachApply
import com.handsome.club.hnh.cookbook.utils.forEachIndexedApply

@Dao
interface FoodDao {

    // Food

    @Transaction
    suspend fun insertFoods(foods: List<FoodEntity>) {
        val foodIds = bulkFoodInsert(foods)

        foods.forEachIndexedApply { index, food ->
            val foodId = foodIds[index]
            food.ingredients
                .forEachApply { it.foodId = foodId }
                .let { bulkIngredientInsert(it) }

            food.feps
                .forEachApply { it.foodId = foodId }
                .let { bulkFepInsert(it) }
        }
    }

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkFoodInsert(foods: List<FoodEntity>): List<Long>

    suspend fun getFoods(pagination: Pagination) = getRecipes(pagination).map { it.food }

    suspend fun getRecipes(pagination: Pagination) = getRecipes(pagination.offset)

    @Transaction
    @Query("SELECT * FROM foods LIMIT :limit")
    fun getRecipes(limit: Int): List<FoodRecipe>


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
