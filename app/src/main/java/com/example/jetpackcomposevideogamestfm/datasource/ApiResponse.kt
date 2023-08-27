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

    @GET("games?key=abe63b7874204e9bad818928d93b5278&dates=2001-01-01,2023-08-31&ordering=-metacritic")
    suspend fun getBestGamesCentury(): Response<GameList>


    @GET("games?key=abe63b7874204e9bad818928d93b5278&platforms=187,18&ordering=-added")
    suspend fun getPlaystation4and5Games(): Response<GameList>

    @GET("games?key=abe63b7874204e9bad818928d93b5278&genres=casual&ordering=-added")
    suspend fun getCasualGames(): Response<GameList>

    @GET("games?key=abe63b7874204e9bad818928d93b5278&developers=4&ordering=-metacritic")
    suspend fun getBethesdaGames(): Response<GameList>



    //Search games by name
    @GET("games?key=abe63b7874204e9bad818928d93b5278&search_exact=true")
    suspend fun getGamesByName(@Query("search") name:String): Response<GameList>


    //Details of a game by id
    @GET("games/{id}?key=abe63b7874204e9bad818928d93b5278")
    suspend fun getGameById(@Path("id") id: Int): Response<GameDetailsModel>

}