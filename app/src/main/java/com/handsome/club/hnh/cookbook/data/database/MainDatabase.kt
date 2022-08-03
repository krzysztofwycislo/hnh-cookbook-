package com.handsome.club.hnh.cookbook.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        FoodEntity::class,
        IngredientEntity::class,
        FepEntity::class,
    ],
    version = 11
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

}