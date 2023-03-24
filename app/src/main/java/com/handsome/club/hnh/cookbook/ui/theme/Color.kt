package com.handsome.club.hnh.cookbook.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun colorsPalette(
    darkTheme: Boolean = isSystemInDarkTheme()
): Colors {
    return if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
}

private val DarkColorPalette = darkColors(
    primary = Color.Black,
    primaryVariant = Color.Black.copy(alpha = .5f),
    secondary = Color(0xFF5c1455),
    background = Color.Black,
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Color(0xFF4d5d53),
    onPrimary = Color.Black,
    secondary = Color(0xFF738678),
)