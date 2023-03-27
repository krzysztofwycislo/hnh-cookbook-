package com.handsome.club.hnh.cookbook.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable


@ExperimentalMaterial3Api
@Composable
fun MainToolbar(title: String) {
    TopAppBar(
        title = {
            Text(text = title)
        }
    )
}