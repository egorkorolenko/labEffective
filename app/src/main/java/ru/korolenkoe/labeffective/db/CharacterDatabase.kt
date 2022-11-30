package ru.korolenkoe.labeffective.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.korolenkoe.labeffective.dao.CharacterDao
import ru.korolenkoe.labeffective.entities.Character
import ru.korolenkoe.labeffective.utils.ThumbnailConverter

@Database(entities = [Character::class], version = 1)
@TypeConverters(ThumbnailConverter::class)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
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
                instance
            }
        }
    }
}
