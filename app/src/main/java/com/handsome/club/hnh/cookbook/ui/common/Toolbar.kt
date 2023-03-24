package com.handsome.club.hnh.cookbook.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


@Composable
fun MainToolbar(title: String) {
    TopAppBar(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
    ) {
        Text(text = title)
    }
}