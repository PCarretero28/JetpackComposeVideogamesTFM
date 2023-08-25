package com.example.jetpackcomposevideogamestfm.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpackcomposevideogamestfm.ui.theme.FavsBackgroundColor

@Composable
fun FavGamesScreen(navController: NavController) {
    //Get the list of games in database

    Column(
        modifier = Modifier
            .background(FavsBackgroundColor)
            .fillMaxSize()
            .padding(start = 4.dp, end = 4.dp, bottom = 32.dp)
    ) {

        LazyColumn {
        }
    }


}
