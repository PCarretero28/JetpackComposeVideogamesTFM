package com.example.jetpackcomposevideogamestfm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposevideogamestfm.model.GameDetailsModel
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
sealed class GamesCenturyState {
    object Loading : GamesCenturyState()
    data class Success(val games: List<GameModel>?) : GamesCenturyState()
    data class Error(val message: String) : GamesCenturyState()
}

sealed class DetailsState {
    object Loading : DetailsState()
    data class Success(val game: GameDetailsModel?) : DetailsState()
    data class Error(val message: String) : DetailsState()
}


@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gameRepositoryImp: GameRepository
): ViewModel() {

    private val _gamesState = mutableStateOf<GamesState>(GamesState.Loading)
    val gamesState: State<GamesState> = _gamesState

    private val _gamesCenturyState = mutableStateOf<GamesCenturyState>(GamesCenturyState.Loading)
    val gamesCenturyState: State<GamesCenturyState> = _gamesCenturyState

    private val _detailsState = mutableStateOf<DetailsState>(DetailsState.Loading)
    val detailsState: State<DetailsState> = _detailsState

    fun getBestGamesOfTheYear(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val gameList = gameRepositoryImp.getBestGamesYear()
                val games = gameList?.topGames // Extract the list of games from GameList
                Log.d("GamesViewModel", games.toString())
                _gamesState.value = GamesState.Success(games)
            }catch (e: Exception){
                _gamesState.value = GamesState.Error("Error al obtener juegos")
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

    fun getDetailsGame(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val gameDetails = gameRepositoryImp.getGameById(id)
                Log.d("GamesViewModel", gameDetails.toString())
                _detailsState.value = DetailsState.Success(gameDetails)
            }catch (e:Exception){
                _detailsState.value = DetailsState.Error("Error al obtener detalles de juego")
            }

        }
    }

}