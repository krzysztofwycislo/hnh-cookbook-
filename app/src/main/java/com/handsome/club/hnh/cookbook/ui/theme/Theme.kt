package com.handsome.club.hnh.cookbook.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun HavenHearthCookbookTheme(
    content: @Composable () -> Unit
) {
    val colors = colorsPalette()
    val typography = typography()

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}


