package com.example.jetpackcomposevideogamestfm.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevideogamestfm.GamesByNameState
import com.example.jetpackcomposevideogamestfm.GamesViewModel
import com.example.jetpackcomposevideogamestfm.ui.theme.MainBackgroundColor

@Composable
fun SearchScreen(navController: NavController) {
    val viewModel: GamesViewModel = hiltViewModel()
    var search: String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
            .padding(horizontal = 4.dp)
    ) {
        // TextField de búsqueda
        SearchGame(search) {
            search = it
        }

        // Función que muestra la lista
        if (search.length > 3) {
            GamesFound(viewModel, navController, search)
        }
    }

    LaunchedEffect(search) {
        if (search.length > 3) {
            viewModel.getGamesByName(search)
        }
    }
}

@Composable
fun SearchGame(search: String, onTextChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = search,
        onValueChange = { newText ->
            onTextChanged(newText)
        },
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Search your favorite games...") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF000000),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            Icon(
                Icons.Default.Clear,
                contentDescription = "clear text",
                modifier = Modifier
                    .clickable {
                        onTextChanged("")
                    }
            )
        }
    )
}
@Composable
fun GamesFound(viewModel: GamesViewModel, navController: NavController, search: String) {
    val gamesByNameState by remember { viewModel.gamesByNameState }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        when (gamesByNameState) {
            is GamesByNameState.Loading -> {
                LoadingState()
            }

            is GamesByNameState.Success -> {
                val games = (gamesByNameState as GamesByNameState.Success).games
                LazyColumn {
                    items(games!!.size) {
                        GameCardItem(games[it], navController, true)
                    }
                }
            }

            is GamesByNameState.Error -> {
                ErrorState()
            }
        }
    }


    LaunchedEffect(true) {
        Log.i("Search", search)
        viewModel.getGamesByName(search)
    }
}