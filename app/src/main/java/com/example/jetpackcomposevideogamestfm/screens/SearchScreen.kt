package com.example.jetpackcomposevideogamestfm.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import com.example.jetpackcomposevideogamestfm.ui.theme.MenuColor

@Composable
fun SearchScreen(navController: NavController) {
    val viewModel: GamesViewModel = hiltViewModel()
    var search: String by remember { mutableStateOf("") }
    var isSearchEnable: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
            .padding(horizontal = 4.dp)
    ) {
        //TextField de busqueda
        SearchGame(search) {
            search = it
            isSearchEnable = enableSearch(search)
        }

        //Boton de búsqueda
        SearchButton(isSearchEnable)

        //Lista de resultados - TO-DO
        GamesFound(viewModel, navController)
    }

}

@Composable
fun SearchButton(searchEnable: Boolean) {
    Button(
        onClick = {
            //Search games and show
        },
        enabled = searchEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MenuColor,
            disabledBackgroundColor = Color(0xFF8ACEF8),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Search")
    }
}

@Composable
fun SearchGame(search: String, onTextChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = search,
        onValueChange = { onTextChanged(it) },
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Search your favorite games...") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF000000),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun GamesFound(viewModel: GamesViewModel, navController: NavController) {
    val gamesByNameState by remember { viewModel.gamesByNameState}

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
        viewModel.getGamesByName("mario kart 8") //Needs parameter "name"
    }
}


fun enableSearch(search: String): Boolean {
    return search.length > 3
}

