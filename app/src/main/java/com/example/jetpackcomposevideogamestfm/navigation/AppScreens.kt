package com.example.jetpackcomposevideogamestfm.navigation

sealed class AppScreens(val route: String){

    object LoginScreen: AppScreens("login_screen")
    object MainScreen: AppScreens("main_screen")
    object FavGamesScreen: AppScreens("fav_games_screen")
    object SearchScreen: AppScreens("search_screen")
    object GameDetailsScreen: AppScreens("game_details_screen")

}
