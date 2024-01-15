package com.handsome.club.hnh.cookbook.data.database.food

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.handsome.club.hnh.cookbook.data.database.food.favorite.FavoriteFoodEntity
import com.squareup.moshi.JsonClass

@Entity(tableName = "foods")
@JsonClass(generateAdapter = true)
data class FoodEntity(

    @ColumnInfo(name = "item_name")
    val itemName: String,

    @ColumnInfo(name = "resource_name")
    val resourceName: String,

    val hunger: Float,

    val energy: Int,

    ) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "food_id", index = true)
    var foodId: Long = 0

    @Ignore
    lateinit var ingredients: List<IngredientEntity>

    @Ignore
    lateinit var feps: List<FepEntity>

    @Ignore
    var isFavorite: Boolean = false

}

@Entity(tableName = "ingredients")
@JsonClass(generateAdapter = true)
data class IngredientEntity(
    val name: String,
    val percentage: Int
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ingredient_id", index = true)
    var ingredientId: Long = 0

    @ColumnInfo(name = "food_id", index = true)
    var foodId: Long = 0

}

@Entity(tableName = "feps")
@JsonClass(generateAdapter = true)
data class FepEntity(
    val name: String,
    val value: Float
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "fep_id", index = true)
    var fepId: Long = 0

    @ColumnInfo(name = "food_id", index = true)
    var foodId: Long = 0

}

data class FoodRecipe(
    @Embedded val food: FoodEntity,

    @Relation(
        parentColumn = "food_id",
        entityColumn = "food_id",
    )
    val ingredients: List<IngredientEntity>,

    @Relation(
        parentColumn = "food_id",
        entityColumn = "food_id",
    )
    val feps: List<FepEntity>,

    @Relation(
        parentColumn = "food_id",
        entityColumn = "food_id",
    )
    val favorite: List<FavoriteFoodEntity>

) {

    init {
        food.ingredients = ingredients
        food.feps = feps
        food.isFavorite = favorite.isNotEmpty()
    }

}
