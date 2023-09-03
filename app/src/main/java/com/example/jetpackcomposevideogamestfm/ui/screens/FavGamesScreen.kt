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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcomposevideogamestfm.navigation.AppScreens
import com.example.jetpackcomposevideogamestfm.ui.theme.MainCardColor
import com.example.jetpackcomposevideogamestfm.ui.theme.TextColor
import com.example.jetpackcomposevideogamestfm.ui.theme.TitleColor
import com.example.jetpackcomposevideogamestfm.ui.theme.color1
import com.example.jetpackcomposevideogamestfm.ui.theme.color2
import com.example.jetpackcomposevideogamestfm.ui.theme.color3
import com.example.jetpackcomposevideogamestfm.ui.theme.color4
import com.example.jetpackcomposevideogamestfm.ui.theme.color5
import com.example.jetpackcomposevideogamestfm.ui.theme.color6

@Composable
fun FavGamesScreen(
    navController: NavController,
    viewModel: FavGamesViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val favGamesUiState by viewModel.gameUiState.collectAsState()

    FavGamesBody(
        gameList = favGamesUiState.gameList,
        navController = navController
    )
}

@Composable
fun FavGamesBody(gameList: List<Game>, navController: NavController) {
    val sortedGameList = gameList.sortedByDescending { it.metacritic }

    Column(
        modifier = Modifier
            .background(FavsBackgroundColor)
            .fillMaxSize()
            .padding(start = 2.dp, end = 2.dp, bottom = 36.dp)
    ) {
        if (gameList.isEmpty()) {
            SearchForFavGames(Modifier.align(Alignment.CenterHorizontally))
        } else {
            FavGamesList(
                gameList = sortedGameList,
                navController = navController
            )
        }
    }
}

@Composable
fun SearchForFavGames(modifier: Modifier) {
    Box(modifier = modifier
        .fillMaxSize()
    ) {
        Text(
            text = "Go and add some of your favorite games!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun FavGamesList(
    gameList: List<Game>,
    navController: NavController
) {
    LazyColumn {
        items(gameList.size) {
            FavGameCardItem(
                juego = gameList[it],
                navController = navController
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavGameCardItem(juego: Game, navController: NavController) {

    val metacriticColor = when (juego.metacritic) {
        in 94..Int.MAX_VALUE -> color1
        in 85..93 -> color2
        in 71..84 -> color3
        in 61..70 -> color4
        in 51..60 -> color5
        in 0 ..1-> Color.White
        else -> color6
    }

    Card(
        onClick = {
            navController.navigate(AppScreens.FavDetailScreen.createRoute(juego.id.toString()))
        },
        modifier = Modifier
            .padding(4.dp)
            .heightIn(max = 100.dp, min = 70.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MainCardColor)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
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
                Spacer(modifier = Modifier.height(4.dp))

                Text(text = juego.name, color = TitleColor)

                Spacer(modifier = Modifier.height(4.dp))

                Row(Modifier.fillMaxWidth()) {
                    Text(text = juego.released.toString(), color = TextColor)
                    Spacer(modifier = Modifier.weight(1F))

                    if (juego.metacritic != 0){
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



