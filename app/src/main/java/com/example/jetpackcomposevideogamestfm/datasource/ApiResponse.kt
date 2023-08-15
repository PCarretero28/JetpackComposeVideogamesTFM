package com.example.jetpackcomposevideogamestfm.datasource

import com.example.jetpackcomposevideogamestfm.model.GameList
import com.example.jetpackcomposevideogamestfm.model.GameModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiResponse {

    @GET("games?key=059d2d9c924248e483e34d34b0ebb31a&dates=2022-01-01,2022-12-31&ordering=rated")
    suspend fun getHighestRatedGames(): Response<GameList>


    @GET("games/326243?key=059d2d9c924248e483e34d34b0ebb31a")
    suspend fun getGameById(): Response<GameModel>
}