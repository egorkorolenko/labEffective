package ru.korolenkoe.lab1effective.db

import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import ru.korolenkoe.lab1effective.models.Character
import ru.korolenkoe.lab1effective.repository.CharacterRepository


class CharacterApplication(context: Context) : Application() {

    val database by lazy { CharacterDatabase.getDatabase(context) }
    val repository by lazy { CharacterRepository(database.characterDao()) }
    val characterViewModel by lazy { CharacterViewModel(repository) }

    fun insert(list: List<Character>) {
        characterViewModel.insertAllCharacters(list)
    }

}