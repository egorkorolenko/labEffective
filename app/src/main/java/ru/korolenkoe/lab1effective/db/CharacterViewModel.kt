package ru.korolenkoe.lab1effective.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.korolenkoe.lab1effective.models.Character
import ru.korolenkoe.lab1effective.repository.CharacterRepository

class CharacterViewModel(private val repository: CharacterRepository):ViewModel() {

    val readAll = repository.readAll

    suspend fun insertCharacter(character: Character) = viewModelScope.launch {
        repository.insertCharacter(character)
    }

    fun insertAllCharacters(characters: List<Character>) = viewModelScope.launch {
        repository.insertAllCharacters(characters)
    }

    suspend fun getAll(): Job = viewModelScope.launch {
        repository.getAll()
    }

    suspend fun getCharacterById(id: Int) = viewModelScope.launch {
        repository.getCharacterById(id)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    class CharacterViewModelFactory(private val repository: CharacterRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(Character::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CharacterViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}