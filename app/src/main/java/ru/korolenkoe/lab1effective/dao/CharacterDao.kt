package ru.korolenkoe.lab1effective.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.korolenkoe.lab1effective.models.Character

interface CharacterDao {

    @Insert
    fun insertCharacter(character: Character)

    @Insert
    fun insertAllCharacters(characters: List<Character>)

    @Query("SELECT * from characters")
    fun getAll(): List<Character>

    @Query("SELECT * from characters WHERE id:= id")
    fun getCharacterById(id: Int): Character

    @Query("DELETE FROM characters")
    fun deleteAll()
}