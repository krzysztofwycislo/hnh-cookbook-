package com.handsome.club.hnh.cookbook.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun colorsPalette(
    darkTheme: Boolean = isSystemInDarkTheme()
): ColorScheme =
    when (val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        (dynamicColor && darkTheme) -> dynamicDarkColorScheme(LocalContext.current)
        (dynamicColor && darkTheme.not()) -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

private val darkColorScheme = darkColorScheme(
    primary = Color.Black,
    secondary = Color(0xFF5c1455),
    background = Color.Black,
)

private val lightColorScheme = lightColorScheme(
    primary = Color.White,
    onPrimary = Color.Black,
    secondary = Color(0xFF738678),
)