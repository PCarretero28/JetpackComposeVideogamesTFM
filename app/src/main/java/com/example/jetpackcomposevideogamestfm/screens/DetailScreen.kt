package com.example.jetpackcomposevideogamestfm.screens

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcomposevideogamestfm.DetailsState
import com.example.jetpackcomposevideogamestfm.GamesViewModel
import com.example.jetpackcomposevideogamestfm.model.DeveloperModel
import com.example.jetpackcomposevideogamestfm.model.GameDetailsModel
import com.example.jetpackcomposevideogamestfm.model.GenreModel
import com.example.jetpackcomposevideogamestfm.model.PlatformModel
import com.example.jetpackcomposevideogamestfm.ui.theme.MainCardColor
import com.example.jetpackcomposevideogamestfm.ui.theme.MenuColor
import com.example.jetpackcomposevideogamestfm.ui.theme.TextColor
import com.example.jetpackcomposevideogamestfm.ui.theme.TitleColor

@Composable
fun DetailScreen(navController: NavController, id: String?) {
    val viewModel: GamesViewModel = hiltViewModel()
    GetGameDetails(viewModel, id, navController)
}

@Composable
fun GetGameDetails(viewModel: GamesViewModel, id: String?, navController: NavController) {
    val detailsState by remember { viewModel.detailsState }

    Box(modifier = Modifier.background(MainCardColor).fillMaxSize()) {
        when (detailsState) {
            is DetailsState.Loading -> {
                LoadingState()
            }

            is DetailsState.Success -> {
                val game = (detailsState as DetailsState.Success).game
                LazyColumn {
                    item {
                        ShowGameDetails(game, navController)
                    }
                }
            }

            is DetailsState.Error -> {
                ErrorState()
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.getDetailsGame(id!!.toInt())
    }
}

@Composable
fun ShowGameDetails(juego: GameDetailsModel?, navController: NavController) {
    Column {

        //Main Image
        Image(
            painter = rememberAsyncImagePainter(model = juego!!.background_image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        //Info with padding
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = juego.name,
                color = TitleColor,
                style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 32.sp),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            )

            //Developers and Released Date
            Row {
                Released(juego.released, modifier = Modifier.weight(1F))

                Spacer(modifier = Modifier.weight(1F))

                Developers(juego.developers, modifier = Modifier.weight(1F))
            }

            Spacer(modifier = Modifier.height(8.dp))

            //Platforms and Genres
            Row {
                Genres(juego.genres, modifier = Modifier.weight(1F))

                Spacer(modifier = Modifier.weight(1F))

                Platforms(juego.platforms, modifier = Modifier.weight(1F))
            }

            Spacer(modifier = Modifier.height(8.dp))

            //Add to database
            Box(Modifier.fillMaxWidth()){
                AddToFavs(juego, modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                )
            }


            Spacer(modifier = Modifier.height(8.dp))

            //Row of images


            //Reviews and Metacritic
            Row {
                Reviews(juego.reviews_count.toString(), modifier = Modifier.weight(1F))

                Spacer(modifier = Modifier.weight(1F))

                Metacritic(juego.metacritic.toString(), modifier = Modifier.weight(1F))
            }

            Spacer(modifier = Modifier.height(8.dp))

            //Description
            Description(juego.description_raw)
        }
    }
}

@Composable
fun AddToFavs(juego: GameDetailsModel?, modifier: Modifier) {
    Box(modifier = modifier){
        FloatingActionButton(
            onClick = { //Add to Database

            },
            backgroundColor = MenuColor,
            contentColor = Color.White
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
        }
    }
}



@Composable
fun Description(description: String) {
    Column(Modifier.padding(8.dp)) {
        Text(text = "Summary", color = TextColor)
        Text(text = description, color = TitleColor)
    }
}

@Composable
fun Platforms(platforms: List<PlatformModel>, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Platforms: ", color = TextColor)
        LazyRow {
            items(platforms.size) {
                val platformName = platforms[it].platform.name
                val textToDisplay = if (it < platforms.size - 1) {
                    "$platformName, "
                } else {
                    platformName
                }
                Text(text = textToDisplay, color = TitleColor)
            }
        }
    }
}

@Composable
fun Genres(genres: List<GenreModel>, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Genres: ", color = TextColor)
        LazyRow {
            items(genres.size) {
                val genreName = genres[it].name
                val textToDisplay = if (it < genres.size - 1) {
                    "$genreName, "
                } else {
                    genreName
                }
                Text(text = textToDisplay, color = TitleColor)
            }
        }

    }
}

@Composable
fun Reviews(reviews: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Reviews count: ", color = TextColor)
        Text(text = reviews, color = TitleColor)
    }
}

@Composable
fun Metacritic(metacritic: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Metacritic: ", color = TextColor)
        Text(text = metacritic, color = TitleColor)
    }
}

@Composable
fun Released(released: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Release date: ", color = TextColor)
        Text(text = released, color = TitleColor)
    }
}

@Composable
fun Developers(developers: List<DeveloperModel>, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Developers: ", color = TextColor)
        LazyRow {
            items(developers.size) {
                val developerName = developers[it].name
                val textToDisplay = if (it < developers.size - 1) {
                    "$developerName, "
                } else {
                    developerName
                }
                Text(text = textToDisplay, color = TitleColor)
            }
        }

    }
}





