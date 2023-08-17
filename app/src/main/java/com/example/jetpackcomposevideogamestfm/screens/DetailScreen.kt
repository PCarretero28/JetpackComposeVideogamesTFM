package com.example.jetpackcomposevideogamestfm.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController, id:String?){

    Box(){
        Text(text = "Id: $id")
    }

}



