package com.example.jetpackcomposevideogamestfm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Game::class], version = 2, exportSchema = false)
@TypeConverters(ListConverters::class)
abstract class CollectionDatabase: RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object{

        @Volatile
        private var Instance: CollectionDatabase? = null

        fun getDatabase(context: Context): CollectionDatabase {
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CollectionDatabase::class.java,
                    "game_database"
                )
                    .fallbackToDestructiveMigration() // Habilita la migración destructiva
                    .build()
                Instance = instance
                instance
            }
        }

    }

}