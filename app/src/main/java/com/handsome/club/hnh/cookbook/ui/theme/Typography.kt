package com.handsome.club.hnh.cookbook.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.handsome.club.hnh.cookbook.R

fun typography(): Typography {
    return Typography().run {
        copy(
            displayLarge = displayLarge.applyFontFamily(),
            displayMedium = displayMedium.applyFontFamily(),
            displaySmall = displaySmall.applyFontFamily(),
            headlineLarge = headlineLarge.applyFontFamily(),
            headlineMedium = headlineMedium.applyFontFamily(),
            headlineSmall = headlineSmall.applyFontFamily(),
            titleLarge = titleLarge.applyFontFamily(),
            titleMedium = titleMedium.applyFontFamily(),
            titleSmall = titleSmall.applyFontFamily(),
            bodyLarge = bodyLarge.applyFontFamily(),
            bodyMedium = bodyMedium.applyFontFamily(),
            bodySmall = bodySmall.applyFontFamily(),
            labelLarge = labelLarge.applyFontFamily(),
            labelMedium = labelMedium.applyFontFamily(),
            labelSmall = labelSmall.applyFontFamily(),
        )
    }
}

private fun TextStyle.applyFontFamily(): TextStyle = copy(fontFamily = Recursive)


private val SpaceGrotesk = FontFamily(
    Font(R.font.space_grotesk_light, FontWeight.Light),
    Font(R.font.space_grotesk_regular, FontWeight.Normal),
    Font(R.font.space_grotesk_semi_bold, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.space_grotesk_medium, FontWeight.Medium),
    Font(R.font.space_grotesk_bold, FontWeight.Bold)
)

private val Recursive = FontFamily(
    Font(R.font.recursive_light, FontWeight.Light),
    Font(R.font.recursive_regular, FontWeight.Normal),
    Font(R.font.recursive_semi_bold, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.recursive_medium, FontWeight.Medium),
    Font(R.font.recursive_bold, FontWeight.Bold)
)

private val FigTree = FontFamily(
    Font(R.font.figtree_light, FontWeight.Light),
    Font(R.font.figtree_regular, FontWeight.Normal),
    Font(R.font.figtree_semi_bold, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.figtree_medium, FontWeight.Medium),
    Font(R.font.figtree_bold, FontWeight.Bold)
)