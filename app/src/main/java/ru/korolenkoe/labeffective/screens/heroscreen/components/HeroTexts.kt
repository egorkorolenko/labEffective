package ru.korolenkoe.labeffective.screens.heroscreen.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
fun HeroDescription(text: String, isExpanded: Boolean, scroll: ScrollState) {
    Text(
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth()
            .verticalScroll(scroll)
            .navigationBarsPadding(),
        text = text,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        maxLines = if (isExpanded) Int.MAX_VALUE else 1
    )
}
