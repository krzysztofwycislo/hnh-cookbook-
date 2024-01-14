package com.handsome.club.hnh.cookbook.data.database.food.favorite

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface FoodFavoriteDao {

    @Transaction
    @Query("SELECT * FROM favorite_food LIMIT :limit")
    fun getFavoriteFoodEntries(limit: Int): List<FavoriteFoodEntity>


}
