package com.example.jetpackcomposevideogamestfm.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposevideogamestfm.data.Game
import com.example.jetpackcomposevideogamestfm.data.GamesRepository
import com.example.jetpackcomposevideogamestfm.model.GameDetailsModel

class GameEntryViewModel(private val gamesRepository: GamesRepository) : ViewModel() {

    suspend fun saveGame(game: GameDetailsModel?) {
        Log.i("GAME", game!!.name)

        gamesRepository.insertGame(game.toGame())

    }
}


fun GameDetailsModel.toGame(): Game = Game(
    id = id,
    name = name,
    description_raw = description_raw,
    metacritic = metacritic,
    released = released,
    background_image = background_image,
    background_image_additional = background_image_additional,
    reviews_count = reviews_count,
    developers = developers.map { it.name },
    genres = genres.map { it.name },
    platforms = platforms.map { it.platform.name }
)