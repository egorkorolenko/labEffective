package ru.korolenkoe.lab1effective.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.korolenkoe.lab1effective.models.Character

@Dao
interface CharacterDao {

    @Insert
     fun insertCharacter(character: Character)

    @Insert
     fun insertAllCharacters(characters: List<Character>)

    @Query("SELECT * from characters")
     fun getAll(): Flow<List<Character>>

    @Query("SELECT * from characters WHERE id=:id")
    fun getCharacterById(id: Int): Character

    @Query("DELETE FROM characters")
    fun deleteAll()
}