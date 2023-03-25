package com.handsome.club.hnh.cookbook.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun VertSpacerXL() {
    VertSpacer(24.dp)
}

@Composable
fun VertSpacerL() {
    VertSpacer(20.dp)
}

@Composable
fun VertSpacerM() {
    VertSpacer(16.dp)
}

@Composable
fun VertSpacerS() {
    VertSpacer(12.dp)
}

@Composable
fun VertSpacerXS() {
    VertSpacer(8.dp)
}

@Composable
fun VertSpacerXXS() {
    VertSpacer(4.dp)
}

@Composable
fun VertSpacerMin() {
    VertSpacer(1.dp)
}

@Composable
private fun VertSpacer(width: Dp) {
    Spacer(modifier = Modifier.height(width))
}


@Composable
fun HorSpacerXL() {
    HorSpacer(24.dp)
}

@Composable
fun HorSpacerL() {
    HorSpacer(20.dp)
}

@Composable
fun HorSpacerM() {
    HorSpacer(16.dp)
}

@Composable
fun HorSpacerS() {
    HorSpacer(12.dp)
}

@Composable
fun HorSpacerXS() {
    HorSpacer(8.dp)
}

@Composable
fun HorSpacerXXS() {
    HorSpacer(4.dp)
}

@Composable
fun HorSpacerMin() {
    HorSpacer(1.dp)
}

@Composable
private fun HorSpacer(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}