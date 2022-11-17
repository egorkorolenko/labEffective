package ru.korolenkoe.lab1effective.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.korolenkoe.lab1effective.dao.CharacterDao
import ru.korolenkoe.lab1effective.models.Character

@Database(entities = [Character::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CharacterDatabase? = null

        fun getDatabase(context: Context): CharacterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDatabase::class.java,
                    "characters"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}