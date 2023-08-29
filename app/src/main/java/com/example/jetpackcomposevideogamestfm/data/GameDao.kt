package com.example.jetpackcomposevideogamestfm.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: Game)

    @Delete
    suspend fun delete(game: Game)

    @Query("SELECT * from games WHERE id = :id")
    fun getItem(id: Int): Flow<Game>

    @Query("SELECT * from games ORDER BY name ASC")
    fun getAllItems(): Flow<List<Game>>

}