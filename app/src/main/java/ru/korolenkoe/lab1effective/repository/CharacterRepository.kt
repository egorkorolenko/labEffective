package ru.korolenkoe.lab1effective.repository

import kotlinx.coroutines.flow.Flow
import ru.korolenkoe.lab1effective.dao.CharacterDao
import ru.korolenkoe.lab1effective.models.Character


class CharacterRepository(private val characterDao: CharacterDao) {

    val readAll : Flow<List<Character>> = characterDao.getAll()

    fun insertAllCharacters(characters: List<Character>) {
        characterDao.insertAllCharacters(characters)
    }

    fun getCharacterById(id: Int): Flow<Character> {
        return characterDao.getCharacterById(id)
    }

}
