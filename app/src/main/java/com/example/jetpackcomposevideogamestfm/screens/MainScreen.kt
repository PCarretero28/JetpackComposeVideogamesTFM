package com.example.jetpackcomposevideogamestfm.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevideogamestfm.GamesState
import com.example.jetpackcomposevideogamestfm.GamesViewModel
import com.example.jetpackcomposevideogamestfm.model.GameModel
import com.example.jetpackcomposevideogamestfm.navigation.AppScreens

@Composable
fun MainScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "MAIN SCREEN")

        Button(
            onClick = {
                //To FavGamesScreen
                navController.navigate(AppScreens.FavGamesScreen.route)

            }) {
            Text(text = "Favs")
        }

        PopularGames()
    }


}

@Composable
fun PopularGames(viewModel: GamesViewModel = hiltViewModel()) {
    val gamesState by remember { viewModel.gamesState }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Highest Rated Games in 2022")

        when (gamesState) {
            is GamesState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator(
                        color = Color.LightGray,
                        strokeWidth = 10.dp
                    )
                }
            }

            is GamesState.Success -> {
                val games = (gamesState as GamesState.Success).games
                LazyRow {
                    items(games!!.size) {
                        ItemDeJuego(games[it])
                    }
                }
            }

            is GamesState.Error -> {
                // Error loading games
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.getGames()
    }
}


@Composable
fun ItemDeJuego(juego: GameModel) {
    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = juego.name)
            Text(text = juego.metacritic.toString())
            Text(text = juego.released)
        }
    }
}
