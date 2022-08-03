package com.handsome.club.hnh.cookbook.data.database

import androidx.room.*

@Entity(tableName = "foods")
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

}

@Entity(tableName = "ingredients")
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

data class FoodWithIngredientsAndFeps(
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
    val feps: List<FepEntity>
) {

    init {
        food.ingredients = ingredients
        food.feps = feps
    }

}
