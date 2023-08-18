package com.example.jetpackcomposevideogamestfm.repository

import android.util.Log
import com.example.jetpackcomposevideogamestfm.datasource.ApiResponse
import com.example.jetpackcomposevideogamestfm.model.GameDetailsModel
import com.example.jetpackcomposevideogamestfm.model.GameList
import com.example.jetpackcomposevideogamestfm.model.GameModel
import javax.inject.Inject


interface GameRepository {

    suspend fun getGameById(id:Int): GameDetailsModel?
    suspend fun getBestGamesYear(): GameList?
    suspend fun getBestGamesCentury(): GameList?
}

class GameRepositoryImp @Inject constructor(
    private val dataSource: ApiResponse
) : GameRepository {

    override suspend fun getBestGamesYear(): GameList? {

        val response = dataSource.getBestGamesYear()
        val gamesList = response.body()

        Log.i("GameList", "Total Games: ${gamesList?.topGames?.size ?: 0}")

        gamesList?.topGames?.forEach { game ->
            Log.d("GameList", "Name: ${game.name}")
        }

        return gamesList
    }

    override suspend fun getBestGamesCentury(): GameList? {
        val response = dataSource.getBestGamesCentury()
        val gamesList = response.body()

        Log.i("GameList", "Total Games: ${gamesList?.topGames?.size ?: 0}")

        gamesList?.topGames?.forEach { game ->
            Log.d("GameList", "Name: ${game.name}")
        }

        return gamesList
    }

    override suspend fun getGameById(id:Int): GameDetailsModel? {

        val response = dataSource.getGameById(id)
        val gameModel= response.body()

        gameModel?.let {
            Log.d("GameDetails", "Id: ${it.id}")
            Log.d("GameDetails", "Name: ${it.name}")
            Log.d("GameDetails", "Released: ${it.released}")
        }

        return gameModel
    }

}