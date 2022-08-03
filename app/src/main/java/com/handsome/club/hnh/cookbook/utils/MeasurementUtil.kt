package com.handsome.club.hnh.cookbook.utils


suspend fun getExecutionTime(block: suspend () -> Unit): Seconds {
    val startTimestamp = System.currentTimeMillis()
    block()
    val endTimestamp = System.currentTimeMillis()
    return ((endTimestamp - startTimestamp) / 1000).toInt()
}