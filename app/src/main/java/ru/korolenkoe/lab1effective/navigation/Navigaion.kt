package ru.korolenkoe.lab1effective.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.korolenkoe.lab1effective.network.ViewModelGetHero
import ru.korolenkoe.lab1effective.network.ViewModelHeroes
import ru.korolenkoe.lab1effective.screens.HeroScreen
import ru.korolenkoe.lab1effective.screens.MainScreen

@Composable
fun Navigation(navController: NavHostController, viewModel: ViewModelHeroes,
               viewModel2: ViewModelGetHero= ViewModelGetHero()) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController, viewModel)
        }
        composable(
            route = Screen.HeroScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                },
            )
        ) { entry ->
            HeroScreen(
                navController = navController,
                entry.arguments?.get("id") as Int,
                viewModel2
            )
        }
    }
}


