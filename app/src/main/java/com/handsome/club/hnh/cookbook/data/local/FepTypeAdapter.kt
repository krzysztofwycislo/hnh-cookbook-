package com.handsome.club.hnh.cookbook.data.local

import com.handsome.club.hnh.cookbook.model.fep.FepType
import com.handsome.club.hnh.cookbook.model.fep.fromApiName
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson


object FepTypeAdapter {

    @ToJson
    fun toJson(fepType: FepType): String {
        return fepType.toString()
    }

    @FromJson
    fun fromJson(fepType: String): FepType {
        return fromApiName(fepType)
    }

}