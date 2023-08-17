@file:OptIn(ExperimentalMaterialApi::class)

package com.example.jetpackcomposevideogamestfm.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcomposevideogamestfm.Games2022State
import com.example.jetpackcomposevideogamestfm.GamesCenturyState
import com.example.jetpackcomposevideogamestfm.GamesViewModel
import com.example.jetpackcomposevideogamestfm.model.GameModel
import com.example.jetpackcomposevideogamestfm.navigation.AppScreens

@Composable
fun MainScreen(navController: NavController) {
    val viewModel: GamesViewModel = hiltViewModel()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        item {
            Button(
                onClick = {
                    //To FavGamesScreen
                    navController.navigate(AppScreens.FavGamesScreen.route)
                }
            ) {
                Text(text = "Favs")
            }
        }
        item {
            Button(
                onClick = {
                    //To SearchScreen
                    navController.navigate(AppScreens.SearchScreen.route)
                }
            ) {
                Text(text = "Search")
            }
        }
        item {
            BestGames2022(viewModel)
        }
        item {
            BestGamesCentury(viewModel)
        }
    }
}

@Composable
fun BestGamesCentury(viewModel: GamesViewModel) {
    val gamesState by remember { viewModel.gamesCenturyState }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Best Games of the Century")

        when (gamesState) {
            is GamesCenturyState.Loading -> {
                LoadingState()
            }

            is GamesCenturyState.Success -> {
                val games = (gamesState as GamesCenturyState.Success).games
                LazyRow {
                    items(games!!.size) {
                        ItemDeJuego(games[it])
                    }
                }
            }

            is GamesCenturyState.Error -> {
                // Error loading games
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.getBestGamesOfTheCentury()
    }
}


@Composable
fun BestGames2022(viewModel: GamesViewModel) {
    val gamesState by remember { viewModel.games2022State }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Highest Rated Games in 2022")

        when (gamesState) {
            is Games2022State.Loading -> {
                LoadingState()
            }

            is Games2022State.Success -> {
                val games = (gamesState as Games2022State.Success).games
                LazyRow {
                    items(games!!.size) {
                        ItemDeJuego(games[it])
                    }
                }
            }

            is Games2022State.Error -> {
                // Error loading games
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.getBestGamesOfTheYear()
    }
}

@Composable
fun LoadingState() {
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


@Composable
fun ItemDeJuego(juego: GameModel) {
    val color1 = Color(0xFF028708)
    val color2 = Color(0xFF88D332)
    val color3 = Color(0xFFFBE627)
    val color4 = Color(0xFFFF9800)
    val color5 = Color(0xFFF62B1C)

    val metacriticColor = when (juego.metacritic) {
        in 95..Int.MAX_VALUE -> color1
        in 90..94 -> color2
        in 85..89 -> color3
        in 79..84 -> color4
        else -> color5
    }

    Card(
        onClick = {
            //Go to GameDetails

        },
        modifier = Modifier
            .padding(8.dp)
            .height(250.dp)
            .widthIn(max = 250.dp, min = 150.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Imagen con un weight de 3
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = juego.background_image),
                    contentDescription = juego.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(3f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = juego.name)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = juego.released)
                Spacer(modifier = Modifier.height(4.dp))
                Row(Modifier.fillMaxWidth()) {
                    Text(text = "${juego.reviews_count} reviews")
                    Spacer(modifier = Modifier.weight(1F))
                    Text(
                        text = juego.metacritic.toString(),
                        color = metacriticColor
                    )
                }

            }
        }
    }
}


