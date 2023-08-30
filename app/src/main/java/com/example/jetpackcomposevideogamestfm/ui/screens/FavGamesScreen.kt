package com.example.jetpackcomposevideogamestfm.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevideogamestfm.data.Game
import com.example.jetpackcomposevideogamestfm.ui.AppViewModelProvider
import com.example.jetpackcomposevideogamestfm.ui.FavGamesViewModel
import com.example.jetpackcomposevideogamestfm.ui.theme.FavsBackgroundColor
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun FavGamesScreen(
    navController: NavController,
    viewModel: FavGamesViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val favGamesUiState by viewModel.gameUiState.collectAsState()

    FavGamesBody(
        gameList = favGamesUiState.gameList
    )
}

@Composable
fun FavGamesBody(gameList: List<Game>) {

    Column(
        modifier = Modifier
            .background(FavsBackgroundColor)
            .fillMaxSize()
            .padding(start = 4.dp, end = 4.dp, bottom = 32.dp)
    ) {
        if (gameList.isEmpty()) {
            Text(
                text = "Go and add some of your favorite games!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
        } else {
            FavGamesList(
                gameList = gameList
            )

        }
    }
}

@Composable
fun FavGamesList(
    gameList: List<Game>
) {
    LazyColumn() {
        items(items = gameList, key = { it.id }) { game ->
            Card {
                Column {
                    Text(text = game.name)
                    Text(text = game.released.toString())
                }
            }
        }
    }
}


