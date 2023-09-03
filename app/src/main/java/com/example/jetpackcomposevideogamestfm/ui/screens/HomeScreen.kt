@file:OptIn(ExperimentalMaterialApi::class)

package com.example.jetpackcomposevideogamestfm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcomposevideogamestfm.GamesBethesdaState
import com.example.jetpackcomposevideogamestfm.GamesCasualState
import com.example.jetpackcomposevideogamestfm.GamesState
import com.example.jetpackcomposevideogamestfm.GamesCenturyState
import com.example.jetpackcomposevideogamestfm.GamesPlaystationState
import com.example.jetpackcomposevideogamestfm.GamesViewModel
import com.example.jetpackcomposevideogamestfm.model.GameModel
import com.example.jetpackcomposevideogamestfm.navigation.AppScreens
import com.example.jetpackcomposevideogamestfm.ui.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: GamesViewModel = hiltViewModel()

    Column(
        Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.dp, end = 4.dp, bottom = 32.dp)
        ) {
            item {
                BestGames2022(viewModel, navController)
            }
            item {
                BestGamesCentury(viewModel, navController)
            }
            item {
                PlaystationGames(viewModel, navController)
            }
            item {
                CasualGames(viewModel, navController)
            }
            item {
                BethesdaGames(viewModel, navController)
            }
        }
    }
}

@Composable
fun BethesdaGames(viewModel: GamesViewModel, navController: NavController) {
    val gamesState by remember { viewModel.gamesBethesdaState }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            color = Color.White,
            text = "Bethesda Softworks Games"
        )

        when (gamesState) {
            is GamesBethesdaState.Loading -> {
                LoadingState()
            }

            is GamesBethesdaState.Success -> {
                val games = (gamesState as GamesBethesdaState.Success).games
                LazyRow {
                    items(games!!.size) {
                        GameCardItem(games[it], navController, false)
                    }
                }
            }

            is GamesBethesdaState.Error -> {
                ErrorState()
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.getBethesdaGames()
    }
}

@Composable
fun CasualGames(viewModel: GamesViewModel, navController: NavController) {
    val gamesState by remember { viewModel.gamesCasualState }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            color = Color.White,
            text = "Casual Games"
        )

        when (gamesState) {
            is GamesCasualState.Loading -> {
                LoadingState()
            }

            is GamesCasualState.Success -> {
                val games = (gamesState as GamesCasualState.Success).games
                LazyRow {
                    items(games!!.size) {
                        GameCardItem(games[it], navController, false)
                    }
                }
            }

            is GamesCasualState.Error -> {
                ErrorState()
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.getCasualGames()
    }
}

@Composable
fun PlaystationGames(viewModel: GamesViewModel, navController: NavController) {
    val gamesState by remember { viewModel.gamesPlaystationState }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            color = Color.White,
            text = "Playstation Games"
        )

        when (gamesState) {
            is GamesPlaystationState.Loading -> {
                LoadingState()
            }

            is GamesPlaystationState.Success -> {
                val games = (gamesState as GamesPlaystationState.Success).games
                LazyRow {
                    items(games!!.size) {
                        GameCardItem(games[it], navController, false)
                    }
                }
            }

            is GamesPlaystationState.Error -> {
                ErrorState()
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.getPlaystationGames()
    }
}

@Composable
fun BestGamesCentury(viewModel: GamesViewModel, navController: NavController) {
    val gamesState by remember { viewModel.gamesCenturyState }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            color = Color.White,
            text = "Best Games of the Century"
        )

        when (gamesState) {
            is GamesCenturyState.Loading -> {
                LoadingState()
            }

            is GamesCenturyState.Success -> {
                val games = (gamesState as GamesCenturyState.Success).games
                LazyRow {
                    items(games!!.size) {
                        GameCardItem(games[it], navController, false)
                    }
                }
            }

            is GamesCenturyState.Error -> {
                ErrorState()
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.getBestGamesOfTheCentury()
    }
}


@Composable
fun BestGames2022(viewModel: GamesViewModel, navController: NavController) {
    val gamesState by remember { viewModel.gamesState }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            color = Color.White,
            text = "Highest Rated Games in 2022"
        )

        when (gamesState) {
            is GamesState.Loading -> {
                LoadingState()
            }

            is GamesState.Success -> {
                val games = (gamesState as GamesState.Success).games
                LazyRow {
                    items(games!!.size) {
                        GameCardItem(games[it], navController, false)
                    }
                }
            }

            is GamesState.Error -> {
                ErrorState()
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
            .padding(90.dp)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator(
            color = Color.LightGray,
            strokeWidth = 8.dp
        )
    }
}

@Composable
fun ErrorState() {
    Box {
        Text(text = "Error getting games from API", color = Color.Red)
    }
}

@Composable
fun GameCardItem(juego: GameModel, navController: NavController, isExpanded: Boolean) {

    val heightCard: Int
    val maxWidthCard : Int

    if (isExpanded) {
        heightCard = 260
        maxWidthCard = 380
    } else {
        heightCard = 230
        maxWidthCard = 250
    }

    var metacriticColor = Color.White

    if(juego.metacritic != null){
        metacriticColor = when (juego.metacritic) {
            in 94..Int.MAX_VALUE -> color1
            in 85..93 -> color2
            in 71..84 -> color3
            in 61..70 -> color4
            in 51..60 -> color5
            else -> color6
        }
    }

    Card(
        onClick = {
            navController.navigate(AppScreens.DetailsScreen.createRoute(juego.id.toString()))
        },
        modifier = Modifier
            .padding(8.dp)
            .height(heightCard.dp)
            .widthIn(max = maxWidthCard.dp, min = 150.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainCardColor)
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
                Text(text = juego.name, color = TitleColor)

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = juego.released, color = TextColor)

                Spacer(modifier = Modifier.height(4.dp))

                Row(Modifier.fillMaxWidth()) {
                    Text(text = "${juego.reviews_count} reviews", color = TextColor)
                    Spacer(modifier = Modifier.weight(1F))

                    if (juego.metacritic != null){
                        Text(
                            text = juego.metacritic.toString(),
                            color = metacriticColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .background(MainCardColor)
                                .padding(2.dp)
                        )
                    }else{
                        Text(
                            text = "X",
                            color = metacriticColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .background(MainCardColor)
                                .padding(2.dp)
                        )
                    }
                }
            }
        }
    }
}


