package com.handsome.club.hnh.cookbook.data.database

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class RealmFood : RealmObject {

    @PrimaryKey
    var id: ObjectId = ObjectId()
    var name: String = ""
    var resourceName: String = ""
    var hunger: Float = 0f
    var energy: Int = 0
    var ingredients: RealmList<RealmIngredient> = realmListOf()
    var feps: RealmList<RealmFep> = realmListOf()
    var isFavorite: Boolean = false

}

class RealmIngredient : EmbeddedRealmObject {

    var name: String = ""
    var percentage: Int = 0

}

class RealmFep : EmbeddedRealmObject {

    var type: String = ""
    var value: Float = 0f

}