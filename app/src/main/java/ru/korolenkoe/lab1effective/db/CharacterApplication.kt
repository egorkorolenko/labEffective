package ru.korolenkoe.lab1effective.db

import android.app.Application
import ru.korolenkoe.lab1effective.models.Character
import ru.korolenkoe.lab1effective.repository.CharacterRepository

class CharacterApplication : Application(){

    private val database by lazy { CharacterDatabase.getDatabase(this) }
    private val repository by lazy { CharacterRepository(database.characterDao()) }
    private val characterViewModel by lazy { CharacterViewModel(repository) }

    suspend fun insert(list: List<Character>){
        characterViewModel.insertAllCharacters(list)
    }
}