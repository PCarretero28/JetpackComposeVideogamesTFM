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

sealed class Games2022State {
    object Loading : Games2022State()
    data class Success(val games: List<GameModel>?) : Games2022State()
    data class Error(val message: String) : Games2022State()
}
sealed class GamesCenturyState {
    object Loading : GamesCenturyState()
    data class Success(val games: List<GameModel>?) : GamesCenturyState()
    data class Error(val message: String) : GamesCenturyState()
}

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gameRepositoryImp: GameRepository
): ViewModel() {

    private val _games2022State = mutableStateOf<Games2022State>(Games2022State.Loading)
    val games2022State: State<Games2022State> = _games2022State

    private val _gamesCenturyState = mutableStateOf<GamesCenturyState>(GamesCenturyState.Loading)
    val gamesCenturyState: State<GamesCenturyState> = _gamesCenturyState

    fun getBestGamesOfTheYear(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val gameList = gameRepositoryImp.getBestGamesYear()
                val games = gameList?.topGames // Extract the list of games from GameList
                Log.d("GamesViewModel", games.toString())
                _games2022State.value = Games2022State.Success(games)
            }catch (e: Exception){
                _games2022State.value = Games2022State.Error("Error al obtener juegos")
            }

        }
    }

    fun getBestGamesOfTheCentury(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val gameList = gameRepositoryImp.getBestGamesCentury()
                val games = gameList?.topGames // Extract the list of games from GameList
                Log.d("GamesViewModel", games.toString())
                _gamesCenturyState.value = GamesCenturyState.Success(games)
            }catch (e: Exception){
                _gamesCenturyState.value = GamesCenturyState.Error("Error al obtener juegos")
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