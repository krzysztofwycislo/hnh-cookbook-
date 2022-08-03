package com.handsome.club.hnh.cookbook.data.food

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
    var foodId: Int = 0

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
    var ingredientId: Int = 0

}

@Entity(tableName = "feps")
data class FepEntity(
    val name: String,
    val value: Float
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "fep_id", index = true)
    var fepId: Int = 0

}

@Entity(primaryKeys = ["food_id", "ingredient_id"])
data class FoodIngredientCrossRef(

    @ColumnInfo(name = "food_id", index = true)
    val foodId: Int,

    @ColumnInfo(name = "ingredient_id", index = true)
    val ingredientId: Int,

    )

@Entity(primaryKeys = ["food_id", "fep_id"])
data class FoodFepCrossRef(

    @ColumnInfo(name = "food_id", index = true)
    val foodId: Long,

    @ColumnInfo(name = "fep_id", index = true)
    val fepId: Long

)

data class FoodWithIngredientsAndFeps(
    @Embedded val food: FoodEntity,

    @Relation(
        parentColumn = "food_id",
        entityColumn = "ingredient_id",
        associateBy = Junction(FoodIngredientCrossRef::class)
    )
    val ingredients: List<IngredientEntity>,

    @Relation(
        parentColumn = "food_id",
        entityColumn = "fep_id",
        associateBy = Junction(FoodFepCrossRef::class)
    )
    val feps: List<FepEntity>
) {

    init {
        food.ingredients = ingredients
        food.feps = feps
    }

}
