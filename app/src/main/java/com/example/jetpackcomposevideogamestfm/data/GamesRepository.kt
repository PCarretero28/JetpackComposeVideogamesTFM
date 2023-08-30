package com.example.jetpackcomposevideogamestfm.data

import kotlinx.coroutines.flow.Flow

interface GamesRepository {

    fun getAllGamesStream(): Flow<List<Game>>

    fun getGameStream(id: Int): Flow<Game?>

    suspend fun insertGame(game: Game)

    suspend fun deleteGame(game: Game)

}