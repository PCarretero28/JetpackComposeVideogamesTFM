package com.example.jetpackcomposevideogamestfm.repository

import com.example.jetpackcomposevideogamestfm.datasource.ApiResponse
import com.example.jetpackcomposevideogamestfm.model.GameDetailsModel
import com.example.jetpackcomposevideogamestfm.model.GameList
import javax.inject.Inject


interface GameRepository {

    suspend fun getGameById(id:Int): GameDetailsModel?
    suspend fun getBestGamesYear(): GameList?
    suspend fun getBestGamesCentury(): GameList?
    suspend fun getCasualGames(): GameList?
    suspend fun getPlaystationGames(): GameList?
    suspend fun getBethesdaGames(): GameList?
    suspend fun getGamesByName(name:String): GameList?

}

class GameRepositoryImp @Inject constructor(
    private val dataSource: ApiResponse
) : GameRepository {

    override suspend fun getBestGamesYear(): GameList? {
        val response = dataSource.getBestGamesYear()
        return response.body()
    }

    override suspend fun getBestGamesCentury(): GameList? {
        val response = dataSource.getBestGamesCentury()
        return response.body()
    }

    override suspend fun getCasualGames(): GameList? {
        val response = dataSource.getCasualGames()
        return response.body()
    }

    override suspend fun getPlaystationGames(): GameList? {
        val response = dataSource.getPlaystation4and5Games()
        return response.body()
    }

    override suspend fun getBethesdaGames(): GameList? {
        val response = dataSource.getBethesdaGames()
        return response.body()
    }

    override suspend fun getGamesByName(name: String): GameList? {
        val response = dataSource.getGamesByName(name)
        return response.body()
    }

    override suspend fun getGameById(id:Int): GameDetailsModel? {
        val response = dataSource.getGameById(id)
        return response.body()
    }
}