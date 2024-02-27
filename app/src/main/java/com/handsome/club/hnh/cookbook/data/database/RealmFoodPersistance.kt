package com.handsome.club.hnh.cookbook.data.database

import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.FoodPersistance
import io.realm.kotlin.Realm
import javax.inject.Inject

class RealmFoodPersistance @Inject constructor(
    private val realm: Realm
) : FoodPersistance {

    override suspend fun isEmpty(): Boolean {
        return realm.query(RealmFood::class).count().find() == 0L
    }

    override suspend fun insertFoods(foods: List<Food>) {
        realm.writeBlocking {
//            copyToRealm(RealmFood().apply {
//                summary = "Do the laundry"
//                isComplete = false
//            })
        }
    }

}