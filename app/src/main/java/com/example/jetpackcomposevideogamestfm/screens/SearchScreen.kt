package com.example.jetpackcomposevideogamestfm.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.jetpackcomposevideogamestfm.ui.theme.MenuColor

@Composable
fun SearchScreen(navController: NavController){
    var search: String by remember { mutableStateOf("") }
    var isSearchEnable: Boolean by remember { mutableStateOf(false) }

    Column {
        //TextField de busqueda
        SearchGame(search){
            search = it
            isSearchEnable = enableSearch(search)
        }

        //Boton de búsqueda
        SearchButton(isSearchEnable)

        //Lista de resultados - TO-DO
        GamesFound()
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
        Text(text = "Search game")
    }
}

@Composable
fun SearchGame(search: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = search,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(text = "Look for your favorite games") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun GamesFound() {

}


fun enableSearch(search: String): Boolean{
    return search.length > 3
}


