package ru.korolenkoe.labeffective.screens.mainscreen.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.korolenkoe.labeffective.R
import ru.korolenkoe.labeffective.db.CharacterDatabase
import ru.korolenkoe.labeffective.entities.Character
import ru.korolenkoe.labeffective.entities.Thumbnail
import ru.korolenkoe.labeffective.repository.CharacterRepositoryDB
import java.io.IOException

class CharacterDBViewModel(application: Application) : ViewModel() {

    private val repository: CharacterRepositoryDB
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val readAll: Flow<List<Character>>

    private var _hero = MutableStateFlow(
        Character(
            1,
            R.string.error.toString(),
            R.string.error.toString(),
            Thumbnail("", "")
        )
    )
    var hero: StateFlow<Character?> = _hero

    init {
        val database = CharacterDatabase.getDatabase(application)
        val dao = database.characterDao()
        repository = CharacterRepositoryDB(dao)
        readAll = repository.readAll
    }

    fun insertAllCharacters(characters: List<Character>) {
        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    repository.insertAllCharacters(characters)
                }
            } catch (e: IOException) {
                Log.e(e.message, "Error")
            }
        }
    }


    fun getCharacterById(id: Int) {
        coroutineScope.launch {
            hero = repository.getCharacterById(id).stateIn(coroutineScope)
        }
    }


    class CharacterViewModelFactory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return CharacterDBViewModel(application) as T
        }
    }
}
