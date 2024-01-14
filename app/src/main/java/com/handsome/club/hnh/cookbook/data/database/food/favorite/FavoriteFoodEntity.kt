package com.handsome.club.hnh.cookbook.data.database.food.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO secure db cleanup

@Entity(tableName = "favorite_food")
data class FavoriteFoodEntity(

    val foodId: Long,

    ) {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "favorite_id", index = true)
    var favoriteId: Long = 0

}
