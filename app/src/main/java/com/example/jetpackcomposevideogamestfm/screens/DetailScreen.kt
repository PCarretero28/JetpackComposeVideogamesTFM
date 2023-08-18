package com.example.jetpackcomposevideogamestfm.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevideogamestfm.DetailsState
import com.example.jetpackcomposevideogamestfm.GamesViewModel
import com.example.jetpackcomposevideogamestfm.model.GameDetailsModel
import com.example.jetpackcomposevideogamestfm.model.GameModel

@Composable
fun DetailScreen(navController: NavController, id: String?) {
    val viewModel: GamesViewModel = hiltViewModel()

    getGameDetails(viewModel, id, navController)

}


@Composable
fun getGameDetails(viewModel: GamesViewModel, id: String?, navController: NavController) {
    val detailsState by remember { viewModel.detailsState }

    Column {

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
                // Error loading details
            }
        }
    }

    LaunchedEffect(key1 = true){
        viewModel.getDetailsGame(id!!.toInt())
    }
}

@Composable
fun ShowGameDetails(juego: GameDetailsModel?, navController: NavController) {

    //Main Image
    MainImage(juego)

    //Title

    //PLatforms

    //Released

    //Developers and Genres

    //Row of images

    //Reviews and Metacritic


    Column {
        Text(text = "Name: ${juego!!.name}")
        Text(text = "Released: ${juego.released}")
        Text(text = "Metacritic: ${juego.metacritic}")
        //Text(text = "Description: ${juego.description_raw}")
        Text(text = "Reviews: ${juego.reviews_count}")
        Column {
            Text(text = "Developers")
            LazyRow(){
                items(juego.developers.size){
                    Text(juego.developers[it].name)
                    Spacer(modifier = Modifier.padding(2.dp))
                }
            }
        }
        Column {
            Text(text = "Genres")
            LazyRow(){
                items(juego.genres.size){
                    Text(juego.genres[it].name)
                    Spacer(modifier = Modifier.padding(2.dp))
                }
            }
        }
        Column {
            Text(text = "Platforms")
            LazyRow(){
                items(juego.platforms.size){
                    Text(juego.platforms[it].platform.name)
                    Spacer(modifier = Modifier.padding(2.dp))
                }
            }
        }

    }

}

@Composable
fun MainImage(juego: GameDetailsModel?) {

}





