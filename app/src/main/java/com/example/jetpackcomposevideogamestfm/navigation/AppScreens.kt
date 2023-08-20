package com.example.jetpackcomposevideogamestfm.navigation

sealed class AppScreens(val route: String){

    object LoginScreen: AppScreens("login_screen")
    object ScaffoldScreens : AppScreens("scaffold_screens")

    object DetailsScreen: AppScreens("game_details_screen/{id}"){
        fun createRoute(id:String)="game_details_screen/$id"
    }

}
