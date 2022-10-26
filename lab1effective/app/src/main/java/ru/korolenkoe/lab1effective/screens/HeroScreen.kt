package ru.korolenkoe.lab1effective.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun HeroScreen(navController: NavController?) {
    HeroName()
}

@Composable
fun HeroName() {
    Text("Hero Name")
}

@Preview
@Composable
fun HeroScreenPreview() {
    HeroScreen(null)
}