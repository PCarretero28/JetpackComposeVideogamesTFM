package com.example.jetpackcomposevideogamestfm.data

import android.content.Context

interface AppContainer{
    val gamesRepository: GamesRepository
}

class AppDataContainer(private val context: Context): AppContainer{

    override val gamesRepository: GamesRepository by lazy{
        OfflineGamesRepository(CollectionDatabase.getDatabase(context).gameDao())
    }


}