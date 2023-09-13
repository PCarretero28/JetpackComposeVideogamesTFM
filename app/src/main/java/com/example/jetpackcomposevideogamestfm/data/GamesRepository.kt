package com.example.jetpackcomposevideogamestfm.data

import kotlinx.coroutines.flow.Flow

interface GamesRepository {

    fun getAllGamesStream(): Flow<List<Game>>

    suspend fun insertGame(game: Game)

    suspend fun deleteGame(game: Game)

}