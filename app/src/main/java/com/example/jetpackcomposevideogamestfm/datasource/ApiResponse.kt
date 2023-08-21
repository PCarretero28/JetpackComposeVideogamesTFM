package com.example.jetpackcomposevideogamestfm.datasource

import com.example.jetpackcomposevideogamestfm.model.GameDetailsModel
import com.example.jetpackcomposevideogamestfm.model.GameList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiResponse {


    //Main lists of games
    @GET("games?key=abe63b7874204e9bad818928d93b5278&dates=2022-01-01,2022-12-31&ordering=-metacritic")
    suspend fun getBestGamesYear(): Response<GameList>

    @GET("games?key=abe63b7874204e9bad818928d93b5278&dates=2000-01-01,2023-07-31&ordering=-metacritic")
    suspend fun getBestGamesCentury(): Response<GameList>

    @GET("games?key=abe63b7874204e9bad818928d93b5278&genres=casual&ordering=-metacritic")
    suspend fun getCasualGames(): Response<GameList>

    @GET("games?key=abe63b7874204e9bad818928d93b5278&platforms=187,18&ordering=-metacritic")
    suspend fun getPlaystation4and5Games(): Response<GameList>


    //List of games by name
    @GET("games?key=abe63b7874204e9bad818928d93b5278")
    suspend fun getGamesByName(@Query("search") name:String): Response<GameList>


    //Details of a game by id
    @GET("games/{id}?key=abe63b7874204e9bad818928d93b5278")
    suspend fun getGameById(@Path("id") id: Int): Response<GameDetailsModel>
}