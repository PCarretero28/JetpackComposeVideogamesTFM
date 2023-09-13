package com.example.jetpackcomposevideogamestfm.data

import kotlinx.coroutines.flow.Flow

class OfflineGamesRepository(private val gameDao: GameDao): GamesRepository {
    override fun getAllGamesStream(): Flow<List<Game>> = gameDao.getAllGames()

    override suspend fun insertGame(game: Game) = gameDao.insert(game)

    override suspend fun deleteGame(game: Game) = gameDao.delete(game)
}