package com.example.jetpackcomposevideogamestfm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class CollectionDatabase: RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object{

        @Volatile
        private var Instance: CollectionDatabase? = null

        fun getDatabase(context: Context): CollectionDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CollectionDatabase::class.java, "game_database")
                    .build()
                    .also { Instance = it }
            }
        }

    }

}