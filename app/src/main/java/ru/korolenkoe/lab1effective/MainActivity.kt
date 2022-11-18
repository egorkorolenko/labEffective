package ru.korolenkoe.lab1effective

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
import ru.korolenkoe.lab1effective.db.CharacterApplication
import ru.korolenkoe.lab1effective.db.CharacterViewModel
import ru.korolenkoe.lab1effective.navigation.Navigation
import ru.korolenkoe.lab1effective.network.ViewModelGetHero
import ru.korolenkoe.lab1effective.network.ViewModelHeroes
import ru.korolenkoe.lab1effective.ui.theme.Lab1effectiveTheme

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
                    val viewModel2: ViewModelGetHero by viewModels()
                    val viewModel: ViewModelHeroes by viewModels()

                    val context = LocalContext.current

                    val characterApplication = CharacterApplication(context)

                    val characterViewModel: CharacterViewModel by viewModels {
                        CharacterViewModel.CharacterViewModelFactory((context.applicationContext as CharacterApplication).repository)
                    }
//                    val characterViewModel = CharacterViewModel()

                    navHostController = rememberNavController()
                    Navigation(navHostController, viewModel, viewModel2, characterViewModel)
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
