package com.example.jetpackcomposevideogamestfm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposevideogamestfm.screens.MainScreen
import com.example.jetpackcomposevideogamestfm.screens.LoginScreen
import com.example.jetpackcomposevideogamestfm.screens.FavGamesScreen
import com.example.jetpackcomposevideogamestfm.screens.DetailScreen
import com.example.jetpackcomposevideogamestfm.screens.SearchScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route){
        composable(route = AppScreens.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(route = AppScreens.MainScreen.route){
            MainScreen(navController)
        }
        composable(route = AppScreens.FavGamesScreen.route){
            FavGamesScreen(navController)
        }
        composable(route = AppScreens.SearchScreen.route){
            SearchScreen(navController)
        }
        composable(route = AppScreens.GameDetailsScreen.route){
            DetailScreen(navController)
        }
    }

}