package com.example.jetpackcomposevideogamestfm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposevideogamestfm.data.Game
import com.example.jetpackcomposevideogamestfm.data.GamesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavGamesViewModel(gamesRepository: GamesRepository): ViewModel(){

    val gameUiState: StateFlow<FavGamesUiState> =
        gamesRepository.getAllGamesStream().map { FavGamesUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FavGamesUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class FavGamesUiState(val gameList: List<Game> = listOf())
