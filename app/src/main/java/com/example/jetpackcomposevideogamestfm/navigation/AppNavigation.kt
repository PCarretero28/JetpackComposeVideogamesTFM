package com.example.jetpackcomposevideogamestfm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposevideogamestfm.screens.LoginScreen
import com.example.jetpackcomposevideogamestfm.screens.DetailScreen
import com.example.jetpackcomposevideogamestfm.screens.ScaffoldScreens

@Composable
fun AppNavigation() {

    val navigationController = rememberNavController()

    NavHost(navController = navigationController, startDestination = AppScreens.ScaffoldScreens.route){
        composable(route = AppScreens.LoginScreen.route){
            LoginScreen(navigationController)
        }
        composable(route = AppScreens.ScaffoldScreens.route){
            ScaffoldScreens(navigationController)
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