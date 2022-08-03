package com.handsome.club.hnh.cookbook.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.handsome.club.hnh.cookbook.data.food.*

@Database(
    entities = [
        FoodEntity::class,

        IngredientEntity::class,
        FoodIngredientCrossRef::class,

        FepEntity::class,
        FoodFepCrossRef::class,
    ],
    version = 1
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

}