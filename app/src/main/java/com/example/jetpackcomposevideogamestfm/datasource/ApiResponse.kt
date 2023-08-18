package com.example.jetpackcomposevideogamestfm.datasource

import com.example.jetpackcomposevideogamestfm.model.GameDetailsModel
import com.example.jetpackcomposevideogamestfm.model.GameList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiResponse {

    @GET("games?key=059d2d9c924248e483e34d34b0ebb31a&dates=2022-01-01,2022-12-31&ordering=-metacritic")
    suspend fun getBestGamesYear(): Response<GameList>

    @GET("games?key=059d2d9c924248e483e34d34b0ebb31a&dates=2000-01-01,2023-07-31&ordering=-metacritic&page=1")
    suspend fun getBestGamesCentury(): Response<GameList>


    @GET("games/{id}?key=059d2d9c924248e483e34d34b0ebb31a")
    suspend fun getGameById(@Path("id") id: Int): Response<GameDetailsModel>
}