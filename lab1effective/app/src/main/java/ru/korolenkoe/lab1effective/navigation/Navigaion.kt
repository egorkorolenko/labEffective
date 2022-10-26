package ru.korolenkoe.lab1effective.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.korolenkoe.lab1effective.screens.HeroScreen
import ru.korolenkoe.lab1effective.screens.MainScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.HeroScreen.route) {
            HeroScreen(navController = navController)
        }
    }
}
