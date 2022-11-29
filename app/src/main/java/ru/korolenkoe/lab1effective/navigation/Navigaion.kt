package ru.korolenkoe.lab1effective.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.korolenkoe.lab1effective.screens.mainscreen.viewmodels.CharacterDBViewModel
import ru.korolenkoe.lab1effective.screens.heroscreen.ViewModelGetHeroApi
import ru.korolenkoe.lab1effective.screens.mainscreen.viewmodels.ViewModelGetHeroesApi
import ru.korolenkoe.lab1effective.screens.heroscreen.HeroScreen
import ru.korolenkoe.lab1effective.screens.mainscreen.MainScreen

@Composable
fun Navigation(
    navController: NavHostController, viewModel: ViewModelGetHeroesApi,
    viewModel2: ViewModelGetHeroApi = ViewModelGetHeroApi(),
    characterDBViewModel: CharacterDBViewModel
) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController, viewModel, characterDBViewModel)
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
                viewModel2,
                characterDBViewModel
            )
        }
    }
}


