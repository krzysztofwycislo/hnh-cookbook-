package com.handsome.club.hnh.cookbook.data.local

import com.handsome.club.hnh.cookbook.model.fep.FepType
import com.handsome.club.hnh.cookbook.model.fep.fromApiName
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter


object FepTypeAdapter : JsonAdapter<FepType>() {

    override fun fromJson(reader: JsonReader): FepType? {
        return if (reader.peek() != JsonReader.Token.NULL) {
            fromApiName(reader.nextString())
        } else {
            reader.nextNull()
        }
    }

    override fun toJson(writer: JsonWriter, value: FepType?) {
        // nothing, one direction mapping
    }


}