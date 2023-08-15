package com.example.jetpackcomposevideogamestfm.model

import com.google.gson.annotations.SerializedName

data class GameList (
    @SerializedName("count") val count: Int,
    @SerializedName("results") val topGames: List<GameModel>
)