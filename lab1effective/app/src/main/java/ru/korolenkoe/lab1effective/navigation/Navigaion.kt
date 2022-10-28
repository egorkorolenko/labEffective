package ru.korolenkoe.lab1effective.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.korolenkoe.lab1effective.R
import ru.korolenkoe.lab1effective.models.HeroItem
import ru.korolenkoe.lab1effective.screens.HeroScreen
import ru.korolenkoe.lab1effective.screens.MainScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.HeroScreen.route + "/{text}/{description}/{image}/{url}",
            arguments = listOf(
                navArgument("text") {
                    type = NavType.IntType
                },
                navArgument("description") {
                    type = NavType.IntType
                },
                navArgument("image") {
                    type = NavType.IntType
                },
                navArgument("url") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            HeroScreen(
                navController = navController, HeroItem(
                    entry.arguments?.get("text") as Int,
                    entry.arguments?.get("description") as Int,
                    entry.arguments?.get("image") as Int,
                    entry.arguments?.get("url") as String
                )
            )
        }
    }
}


