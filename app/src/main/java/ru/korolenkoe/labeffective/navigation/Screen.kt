package ru.korolenkoe.labeffective.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen(route = "home_screen")
    object HeroScreen : Screen(route = "hero_screen")
}
