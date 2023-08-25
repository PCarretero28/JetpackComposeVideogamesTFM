package com.example.jetpackcomposevideogamestfm.screens

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.jetpackcomposevideogamestfm.navigation.AppScreens
import com.example.jetpackcomposevideogamestfm.ui.theme.MenuColor


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldScreens(navController: NavController) {
    var currentScreen by remember { mutableStateOf(Screen.Home) }
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            MyTopAppBar(
                onClickIcon = {
                    navController.navigate(AppScreens.LoginScreen.route)
                }
            )
        },
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomNavigation(currentScreen, onScreenSelected = { screen -> currentScreen = screen }) },
    ) {
        // Contenido de la pantalla actual
        when (currentScreen) {
            Screen.Search -> SearchScreen(navController)
            Screen.Home -> MainScreen(navController)
            Screen.Favorites -> FavGamesScreen(navController)
        }
    }
}

@Composable
fun MyTopAppBar(
    onClickIcon:(String) -> Unit
) {
    TopAppBar(
        title = { Text(text = "RAWG Games") },
        backgroundColor = MenuColor,
        contentColor = Color.White,

        actions = {
            IconButton(onClick = { onClickIcon("Log out") }) {
                Icon(imageVector = Icons.Default.Logout, contentDescription = "login")
            }
        },
    )
}

@Composable
fun MyBottomNavigation(currentScreen: Screen, onScreenSelected: (Screen) -> Unit) {
    var index by remember { mutableStateOf(currentScreen.ordinal) }

    BottomNavigation(backgroundColor = MenuColor, contentColor = Color.White) {
        BottomNavigationItem(
            selected = currentScreen == Screen.Search,
            onClick = {
                index = Screen.Search.ordinal
                onScreenSelected(Screen.Search)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search"
                )
            },
            label = { Text(text = "Search") }
        )
        BottomNavigationItem(
            selected = currentScreen == Screen.Home,
            onClick = {
                index = Screen.Home.ordinal
                onScreenSelected(Screen.Home)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home"
                )
            },
            label = { Text(text = "Home") }
        )
        BottomNavigationItem(
            selected = currentScreen == Screen.Favorites,
            onClick = {
                index = Screen.Favorites.ordinal
                onScreenSelected(Screen.Favorites)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "fav"
                )
            },
            label = { Text(text = "Favorites") }
        )
    }
}


