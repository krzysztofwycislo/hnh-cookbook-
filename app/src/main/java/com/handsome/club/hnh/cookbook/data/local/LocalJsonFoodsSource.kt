package com.handsome.club.hnh.cookbook.data.local

import android.content.res.Resources
import com.handsome.club.hnh.cookbook.R
import com.handsome.club.hnh.cookbook.data.database.FoodEntity
import com.handsome.club.hnh.cookbook.model.food.FoodsSource
import com.handsome.club.hnh.cookbook.utils.loadJsonFromRawResource
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

class LocalJsonFoodsSource @Inject constructor(
    moshi: Moshi,
    private val resources: Resources
) : FoodsSource {

    private val type = Types.newParameterizedType(MutableList::class.java, FoodEntity::class.java)
    private val foodAdapter: JsonAdapter<List<FoodEntity>> = moshi.adapter(type)

    override suspend fun fetchAllFoods(): List<FoodEntity> {
        return resources.loadJsonFromRawResource(R.raw.food_info)
            ?.let(foodAdapter::fromJson)
            ?: throw IllegalStateException("Foods parsed from local json are empty")
    }

}