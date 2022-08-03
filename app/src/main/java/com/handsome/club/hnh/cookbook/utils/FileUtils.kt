package com.handsome.club.hnh.cookbook.utils

import android.content.res.Resources
import androidx.annotation.RawRes
import timber.log.Timber

fun Resources.loadJsonFromRawResource(@RawRes rawResId: Int): String? {
    val foodsInputStream = openRawResource(rawResId)

    return try {
        val size = foodsInputStream.available()

        val buffer = ByteArray(size)

        foodsInputStream.read(buffer)

        String(buffer, Charsets.UTF_8)
    } catch (e: Exception) {
        Timber.e(e)
        null
    } finally {
        foodsInputStream.close()
    }
}