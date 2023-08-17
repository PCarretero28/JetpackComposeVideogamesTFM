package com.example.jetpackcomposevideogamestfm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposevideogamestfm.screens.MainScreen
import com.example.jetpackcomposevideogamestfm.screens.LoginScreen
import com.example.jetpackcomposevideogamestfm.screens.FavGamesScreen
import com.example.jetpackcomposevideogamestfm.screens.DetailScreen
import com.example.jetpackcomposevideogamestfm.screens.SearchScreen

@Composable
fun AppNavigation() {

    val navigationController = rememberNavController()

    NavHost(navController = navigationController, startDestination = AppScreens.MainScreen.route){
        composable(route = AppScreens.LoginScreen.route){
            LoginScreen(navigationController)
        }
        composable(route = AppScreens.MainScreen.route){
            MainScreen(navigationController)
        }
        composable(route = AppScreens.FavGamesScreen.route){
            FavGamesScreen(navigationController)
        }
        composable(route = AppScreens.SearchScreen.route){
            SearchScreen(navigationController)
        }
        composable(
            route = AppScreens.DetailsScreen.route,
            arguments = listOf(navArgument("id") { defaultValue = "326243"})
        ){backStackEntry->
            DetailScreen(
                navController = navigationController,
                id = backStackEntry.arguments?.getString("id")
            )
        }
    }

}