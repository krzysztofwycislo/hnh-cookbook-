package com.handsome.club.hnh.cookbook.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.handsome.club.hnh.cookbook.data.database.food.FepEntity
import com.handsome.club.hnh.cookbook.data.database.food.FoodDao
import com.handsome.club.hnh.cookbook.data.database.food.FoodEntity
import com.handsome.club.hnh.cookbook.data.database.food.IngredientEntity
import com.handsome.club.hnh.cookbook.data.database.food.favorite.FavoriteFoodEntity

@Database(
    entities = [
        FoodEntity::class,
        IngredientEntity::class,
        FepEntity::class,
        FavoriteFoodEntity::class,
    ],
    version = 16
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

}