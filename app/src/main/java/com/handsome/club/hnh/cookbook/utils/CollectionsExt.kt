package com.handsome.club.hnh.cookbook.utils

fun <T> List<T>.forEachApply(block: (T) -> Unit): List<T> {
    forEach { block(it) }
    return this
}