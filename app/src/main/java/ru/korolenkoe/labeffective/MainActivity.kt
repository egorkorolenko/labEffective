package ru.korolenkoe.labeffective

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.korolenkoe.labeffective.navigation.Navigation
import ru.korolenkoe.labeffective.screens.heroscreen.ViewModelGetHeroApi
import ru.korolenkoe.labeffective.screens.mainscreen.viewmodels.CharacterDBViewModel
import ru.korolenkoe.labeffective.screens.mainscreen.viewmodels.ViewModelGetHeroesApi
import ru.korolenkoe.labeffective.ui.theme.Lab1effectiveTheme

class MainActivity : ComponentActivity() {

    lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1effectiveTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModelGetHeroApi: ViewModelGetHeroApi by viewModels()
                    val viewModelGetHeroesApi: ViewModelGetHeroesApi by viewModels()

                    val context = LocalContext.current

                    val characterDBViewModel: CharacterDBViewModel by viewModels {
                        CharacterDBViewModel.CharacterViewModelFactory((context.applicationContext as Application))
                    }
                    navHostController = rememberNavController()
                    Navigation(navHostController, viewModelGetHeroesApi, viewModelGetHeroApi, characterDBViewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    MainScreen(null, null)
}
