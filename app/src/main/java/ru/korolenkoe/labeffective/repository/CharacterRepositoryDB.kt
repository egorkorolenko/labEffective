package ru.korolenkoe.labeffective.repository

import kotlinx.coroutines.flow.Flow
import ru.korolenkoe.labeffective.dao.CharacterDao
import ru.korolenkoe.labeffective.entities.Character


class CharacterRepositoryDB(private val characterDao: CharacterDao) {

    val readAll : Flow<List<Character>> = characterDao.getAll()

    fun insertAllCharacters(characters: List<Character>) {
        characterDao.insertAllCharacters(characters)
    }

    fun getCharacterById(id: Int): Flow<Character> {
        return characterDao.getCharacterById(id)
    }

}
