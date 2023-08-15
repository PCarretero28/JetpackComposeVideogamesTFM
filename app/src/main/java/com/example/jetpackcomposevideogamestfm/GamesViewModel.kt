package com.example.jetpackcomposevideogamestfm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposevideogamestfm.model.GameModel
import com.example.jetpackcomposevideogamestfm.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class GamesState {
    object Loading : GamesState()
    data class Success(val games: List<GameModel>?) : GamesState()
    data class Error(val message: String) : GamesState()
}

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gameRepositoryImp: GameRepository
): ViewModel() {

    private val _gamesState = mutableStateOf<GamesState>(GamesState.Loading)
    val gamesState: State<GamesState> = _gamesState

    fun getGames(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val gameList = gameRepositoryImp.getHighestRatedGames()
                val games = gameList?.topGames // Extract the list of games from GameList
                Log.d("GamesViewModel", games.toString())
                _gamesState.value = GamesState.Success(games)
            }catch (e: Exception){
                _gamesState.value = GamesState.Error("Error al obtener juegos")
            }

        }
    }

    fun getDetailsGame(){
        viewModelScope.launch(Dispatchers.IO) {
            val gameDetails = gameRepositoryImp.getGameById()
            Log.d("GamesViewModel", gameDetails.toString())
        }
    }

}