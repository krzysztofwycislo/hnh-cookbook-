package com.handsome.club.hnh.cookbook.data.database.food.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface FavoriteFoodDao {

    @Query("SELECT * FROM favorite_food WHERE foodId = :foodId LIMIT 1")
    suspend fun getFavoriteFood(foodId: Long): FavoriteFoodEntity?

    @Transaction
    @Query("SELECT * FROM favorite_food LIMIT :limit")
    fun getFavoriteFoods(limit: Int): List<FavoriteFoodEntity>

    @Query("DELETE FROM favorite_food WHERE favorite_id = :favoriteId")
    fun deleteFavoriteFood(favoriteId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteFood(favoriteFoodEntity: FavoriteFoodEntity)

    suspend fun toggleFavoriteFood(foodId: Long): Boolean {
        val favoriteFood = getFavoriteFood(foodId)

        return if (favoriteFood == null) {
            insertFavoriteFood(FavoriteFoodEntity(foodId))
            true
        } else {
            deleteFavoriteFood(favoriteFood.favoriteId)
            false
        }
    }

}
