package com.handsome.club.hnh.cookbook.utils

fun <T> List<T>.forEachApply(block: (T) -> Unit): List<T> {
    forEach { block(it) }
    return this
}

suspend fun <T> List<T>.forEachIndexedApply(block: suspend (Int, T) -> Unit): List<T> {
    forEachIndexed { index, item -> block(index, item) }
    return this
}

fun <T> Set<T>.addOrRemove(element: T): Set<T> {
    return if (contains(element)) {
        minus(element)
    } else {
        plus(element)
    }
}