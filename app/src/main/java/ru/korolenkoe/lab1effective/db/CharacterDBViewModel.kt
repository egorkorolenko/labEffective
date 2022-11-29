package ru.korolenkoe.lab1effective.db

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.korolenkoe.lab1effective.entities.Character
import ru.korolenkoe.lab1effective.entities.Thumbnail
import ru.korolenkoe.lab1effective.repository.CharacterRepositoryDB

class CharacterDBViewModel(application: Application) : ViewModel() {

    private val repository: CharacterRepositoryDB
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val readAll: Flow<List<Character>>

    private var _hero = MutableStateFlow(Character(1, "Error", "Error", Thumbnail("", "")))
    var hero: StateFlow<Character?> = _hero

    init {
        val database = CharacterDatabase.getDatabase(application)
        val dao = database.characterDao()
        repository = CharacterRepositoryDB(dao)
        readAll = repository.readAll
    }

    fun insertAllCharacters(characters: List<Character>) {
        coroutineScope.launch {
            repository.insertAllCharacters(characters)
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
