package com.example.jetpackcomposevideogamestfm.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposevideogamestfm.data.Game
import com.example.jetpackcomposevideogamestfm.data.GamesRepository
import com.example.jetpackcomposevideogamestfm.model.GameDetailsModel

class GameEntryViewModel(private val gamesRepository: GamesRepository): ViewModel() {

    var gameUiState by mutableStateOf(GameUiState())
        private set

    suspend fun saveGame(game: GameDetailsModel?){
        Log.i("GAME", game!!.name)

        gamesRepository.insertGame(game.toGame())

    }
}

data class GameUiState(
    val gameDetails: GameDetails = GameDetails(),
    val isEntryValid: Boolean = false
)

data class GameDetails(
    val id: Int = 0,
    val name: String = "",
    val description_raw: String = "",
    val metacritic: Int = 0,
    val released: String = "",
    val background_image: String = "",
    val background_image_additional: String = "",
    val reviews_count: Int = 0,
)


fun GameDetailsModel.toGame(): Game = Game(
    id = id,
    name = name,
    description_raw = description_raw,
    metacritic = metacritic,
    released = released,
    background_image = background_image,
    background_image_additional = background_image_additional,
    reviews_count = reviews_count
)