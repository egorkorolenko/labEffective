package ru.korolenkoe.lab1effective.repository

import androidx.annotation.WorkerThread
import ru.korolenkoe.lab1effective.dao.CharacterDao
import ru.korolenkoe.lab1effective.models.Character

class CharacterRepository(private val characterDao: CharacterDao) {

    val readAll: List<Character> = characterDao.getAll()

    @WorkerThread
    suspend fun insertCharacter(character: Character) {
        characterDao.insertCharacter(character)
    }

    suspend fun insertAllCharacters(characters: List<Character>) {
        characterDao.insertAllCharacters(characters)
    }

    fun getAll(): List<Character> {
        return characterDao.getAll()
    }

    @WorkerThread
    fun getCharacterById(id: Int): Character{
        return characterDao.getCharacterById(id)
    }

    fun deleteAll(){
        characterDao.deleteAll()
    }

}