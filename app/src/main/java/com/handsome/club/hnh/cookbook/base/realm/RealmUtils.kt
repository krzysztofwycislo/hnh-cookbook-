package com.handsome.club.hnh.cookbook.base.realm

import org.mongodb.kbson.BsonObjectId


fun String?.createRealmId() = this?.takeIf { it.isNotBlank() }
    ?.let { BsonObjectId(it) }
    ?: BsonObjectId()