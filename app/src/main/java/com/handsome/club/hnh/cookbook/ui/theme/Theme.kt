package com.handsome.club.hnh.cookbook.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun HavenHearthCookbookTheme(
    content: @Composable () -> Unit
) {
    val colors = colorsPalette()

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}


