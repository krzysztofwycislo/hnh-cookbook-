package com.handsome.club.hnh.cookbook.data.database

import com.handsome.club.hnh.cookbook.base.paging.Pagination
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.FoodFilters
import com.handsome.club.hnh.cookbook.model.food.FoodPersistance
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import javax.inject.Inject

class RealmFoodPersistance @Inject constructor(
    private val realm: Realm
) : FoodPersistance {

    override suspend fun isEmpty(): Boolean {
        return realm.query(RealmFood::class).count().find() == 0L
    }

    override suspend fun populateFoods(foods: List<Food>) {
        realm.write {
            delete(RealmFood::class)

            foods.map {
                copyToRealm(it.toRealm())
            }
        }
    }

    override suspend fun getFoods(paging: Pagination, filters: FoodFilters): List<Food> {
        return realm.query<RealmFood>()
            .limit(paging.offset)
            .find()
            .map { it.toModel() }
    }

}