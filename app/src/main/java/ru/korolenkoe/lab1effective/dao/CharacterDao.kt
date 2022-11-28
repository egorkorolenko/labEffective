package ru.korolenkoe.lab1effective.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.korolenkoe.lab1effective.models.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertAllCharacters(characters: List<Character>)

    @Query("SELECT * from characters")
    fun getAll(): Flow<List<Character>>

    @Query("SELECT * from characters WHERE id=:id")
    fun getCharacterById(id: Int): Flow<Character>

}
