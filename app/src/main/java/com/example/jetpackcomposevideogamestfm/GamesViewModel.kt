package com.example.jetpackcomposevideogamestfm

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

sealed class GamesCasualState {
    object Loading : GamesCasualState()
    data class Success(val games: List<GameModel>?) : GamesCasualState()
    data class Error(val message: String) : GamesCasualState()
}

sealed class GamesPlaystationState {
    object Loading : GamesPlaystationState()
    data class Success(val games: List<GameModel>?) : GamesPlaystationState()
    data class Error(val message: String) : GamesPlaystationState()
}

sealed class DetailsState {
    object Loading : DetailsState()
    data class Success(val game: GameDetailsModel?) : DetailsState()
    data class Error(val message: String) : DetailsState()
}

sealed class GamesByNameState {
    object Loading : GamesByNameState()
    data class Success(val games: List<GameModel>?) : GamesByNameState()
    data class Error(val message: String) : GamesByNameState()
}

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gameRepositoryImp: GameRepository
): ViewModel() {

    private val _gamesState = mutableStateOf<GamesState>(GamesState.Loading)
    val gamesState: State<GamesState> = _gamesState

    private val _gamesCenturyState = mutableStateOf<GamesCenturyState>(GamesCenturyState.Loading)
    val gamesCenturyState: State<GamesCenturyState> = _gamesCenturyState

    private val _gamesCasualState = mutableStateOf<GamesCasualState>(GamesCasualState.Loading)
    val gamesCasualState: State<GamesCasualState> = _gamesCasualState

    private val _gamesPlaystationState = mutableStateOf<GamesPlaystationState>(GamesPlaystationState.Loading)
    val gamesPlaystationState: State<GamesPlaystationState> = _gamesPlaystationState

    private val _detailsState = mutableStateOf<DetailsState>(DetailsState.Loading)
    val detailsState: State<DetailsState> = _detailsState

    private val _gamesByNameState = mutableStateOf<GamesByNameState>(GamesByNameState.Loading)
    val gamesByNameState: State<GamesByNameState> = _gamesByNameState

    fun getBestGamesOfTheYear(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val gameList = gameRepositoryImp.getBestGamesYear()
                val games = gameList?.topGames
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
                val games = gameList?.topGames
                _gamesCenturyState.value = GamesCenturyState.Success(games)
            }catch (e: Exception){
                _gamesCenturyState.value = GamesCenturyState.Error("Error al obtener juegos")
            }

        }
    }

    fun getCasualGames(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val gameList = gameRepositoryImp.getCasualGames()
                val games = gameList?.topGames
                _gamesCasualState.value = GamesCasualState.Success(games)
            }catch (e: Exception){
                _gamesCasualState.value = GamesCasualState.Error("Error al obtener juegos")
            }

        }
    }

    fun getPlaystationGames(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val gameList = gameRepositoryImp.getPlaystationGames()
                val games = gameList?.topGames
                _gamesPlaystationState.value = GamesPlaystationState.Success(games)
            }catch (e: Exception){
                _gamesPlaystationState.value = GamesPlaystationState.Error("Error al obtener juegos")
            }

        }
    }

    fun getGamesByName(name:String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val gameList = gameRepositoryImp.getGamesByName(name)
                val games = gameList?.topGames
                _gamesByNameState.value = GamesByNameState.Success(games)
            }catch (e: Exception){
                _gamesByNameState.value = GamesByNameState.Error("Error al obtener juegos")
            }

        }
    }


    fun getDetailsGame(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val gameDetails = gameRepositoryImp.getGameById(id)
                _detailsState.value = DetailsState.Success(gameDetails)
            }catch (e:Exception){
                _detailsState.value = DetailsState.Error("Error al obtener detalles de juego")
            }

        }
    }

}