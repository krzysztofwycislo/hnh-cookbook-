package com.handsome.club.hnh.cookbook.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.handsome.club.hnh.cookbook.model.fep.FepType
import kotlin.reflect.KClass

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

private val fepSignatureColors = mapOf(
    FepType.Strength::class to Color(0xFFBF9794),
    FepType.Agility::class to Color(0xFF9991DC),
    FepType.Constitution::class to Color(0xFFc29ab4),
    FepType.Perception::class to Color(0xFFE4BF98),
    FepType.Dexterity::class to Color(0xFFFEFDCC),
    FepType.Intelligence::class to Color(0xFF9DB7B9),
    FepType.Charisma::class to Color(0xFF9BEEB1),
    FepType.Will::class to Color(0xFFE4F38F),
    FepType.Psyche::class to Color(0xFFC48DFD),
)

fun KClass<out FepType>.determineFepSignatureColor(): Color = fepSignatureColors.getValue(this)