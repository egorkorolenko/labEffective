package ru.korolenkoe.labeffective.screens.heroscreen.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun HeroName(text: String) {
    Text(
        text = text,
        fontSize = 30.sp,
        fontFamily = FontFamily.Serif,
        color = Color.White,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun HeroDescription(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        color = Color.White,
        fontWeight = FontWeight.Bold,
    )
}
