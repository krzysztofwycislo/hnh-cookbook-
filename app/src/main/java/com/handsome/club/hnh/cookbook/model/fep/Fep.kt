package com.handsome.club.hnh.cookbook.model.fep

import com.squareup.moshi.Json


data class Fep(

    @Json(ignore = true)
    val id: Long = 0,

    val name: String,
    val value: Float,
) {

    @Json(ignore = true)
    val type: FepType = determineFepType()

}