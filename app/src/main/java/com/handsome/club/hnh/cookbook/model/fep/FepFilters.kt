package com.handsome.club.hnh.cookbook.model.fep

import android.util.Range


data class FepFilters(
    val name: String,
    val value: Range<Float>,
)