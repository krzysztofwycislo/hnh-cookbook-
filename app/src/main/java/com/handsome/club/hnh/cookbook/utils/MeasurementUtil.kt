package com.handsome.club.hnh.cookbook.utils

import timber.log.Timber


inline fun <T> logExecutionTime(tag: String = "", block: () -> T): T {
    Timber.i("$tag Elapse Start")

    val startTimestamp = System.currentTimeMillis()
    val result = block()
    val endTimestamp = System.currentTimeMillis()

    val executionTime = endTimestamp - startTimestamp

    Timber.i("$tag Elapsed time = $executionTime ms")

    return result
}