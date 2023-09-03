package com.example.jetpackcomposevideogamestfm.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcomposevideogamestfm.DetailsState
import com.example.jetpackcomposevideogamestfm.GamesViewModel
import com.example.jetpackcomposevideogamestfm.model.DeveloperModel
import com.example.jetpackcomposevideogamestfm.model.GameDetailsModel
import com.example.jetpackcomposevideogamestfm.model.GenreModel
import com.example.jetpackcomposevideogamestfm.model.PlatformModel
import com.example.jetpackcomposevideogamestfm.navigation.AppScreens
import com.example.jetpackcomposevideogamestfm.ui.AppViewModelProvider
import com.example.jetpackcomposevideogamestfm.ui.GameEntryDeleteViewModel
import com.example.jetpackcomposevideogamestfm.ui.theme.DeleteColor
import com.example.jetpackcomposevideogamestfm.ui.theme.MainCardColor
import com.example.jetpackcomposevideogamestfm.ui.theme.MenuColor
import com.example.jetpackcomposevideogamestfm.ui.theme.TextColor
import com.example.jetpackcomposevideogamestfm.ui.theme.TitleColor
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    navController: NavController,
    id: String?,
    viewModel: GameEntryDeleteViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val gamesViewModel: GamesViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            DetailsTopBar(
                onClickIcon = {
                    navController.popBackStack()
                }
            )
        },
    ) {
        GetGameDetails(
            viewModel,
            gamesViewModel,
            id,
            navController,
            false
        )
    }

}

@Composable
fun DetailsTopBar(
    onClickIcon: (String) -> Unit
) {
    TopAppBar(
        title = {  },
        backgroundColor = MainCardColor,
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { onClickIcon("Go back") }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }
        },
    )
}

@Composable
fun GetGameDetails(
    viewModel: GameEntryDeleteViewModel,
    gamesViewModel: GamesViewModel,
    id: String?,
    navController: NavController,
    isInDatabase: Boolean
) {
    val detailsState by remember { gamesViewModel.detailsState }

    Box(modifier = Modifier
        .background(MainCardColor)
        .fillMaxSize()) {
        when (detailsState) {
            is DetailsState.Loading -> {
                LoadingState()
            }

            is DetailsState.Success -> {
                val game = (detailsState as DetailsState.Success).game
                LazyColumn {
                    item {
                        ShowGameDetails(
                            juego = game,
                            navController = navController,
                            viewModel = viewModel,
                            isInDatabase = isInDatabase
                        )
                    }
                }
            }

            is DetailsState.Error -> {
                ErrorState()
            }

        }
    }

    LaunchedEffect(true) {
        gamesViewModel.getDetailsGame(id!!.toInt())
    }
}

@Composable
fun ShowGameDetails(
    juego: GameDetailsModel?,
    navController: NavController,
    viewModel: GameEntryDeleteViewModel,
    isInDatabase: Boolean
) {

    Column {
        //Main Image
        Image(
            painter = rememberAsyncImagePainter(model = juego!!.background_image),
            contentDescription = "${juego.name} main image",
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

            //Si está en database, opción de borrar
            //Si no está en database, opción de añadir

            if(isInDatabase){
                //Delete from database
                Box(Modifier.fillMaxWidth()){
                    DeleteFromCollection(juego,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .wrapContentSize(Alignment.Center),
                        navController,
                        viewModel = viewModel
                    )
                }
            }else{
                //Add to database
                Box(Modifier.fillMaxWidth()){
                    AddToFavs(juego,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .wrapContentSize(Alignment.Center),
                        navController,
                        viewModel = viewModel
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            //Extra image
            Card {
                Image(
                    painter = rememberAsyncImagePainter(model = juego.background_image_additional),
                    contentDescription = "extra image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

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
fun AddToFavs(
    game: GameDetailsModel?,
    modifier: Modifier,
    navController: NavController,
    viewModel: GameEntryDeleteViewModel
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier){
        FloatingActionButton(
            onClick = {
                Log.i("GAME", "AddToFavs")
                coroutineScope.launch {
                    viewModel.saveGame(game)
                    Toast.makeText(context, "Has añadido ${game!!.name} con éxito", Toast.LENGTH_SHORT).show()
                    navController.navigate(AppScreens.ScaffoldScreens.route)
                }
            },
            backgroundColor = MenuColor,
            contentColor = Color.White
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
        }
    }
}
@Composable
fun DeleteFromCollection(
    game: GameDetailsModel?,
    modifier: Modifier,
    navController: NavController,
    viewModel: GameEntryDeleteViewModel
){
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier){
        FloatingActionButton(
            onClick = {
                Log.i("GAME", "DeleteFromCollection")
                coroutineScope.launch {
                    viewModel.deleteGame(game)
                    Toast.makeText(context, "Has borrado ${game!!.name} de tu lista", Toast.LENGTH_SHORT).show()
                    navController.navigate(AppScreens.ScaffoldScreens.route)
                }
            },
            backgroundColor = DeleteColor,
            contentColor = Color.White
        ) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
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





