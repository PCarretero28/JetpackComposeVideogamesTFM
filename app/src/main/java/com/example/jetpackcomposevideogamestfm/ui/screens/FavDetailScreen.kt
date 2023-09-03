package com.example.jetpackcomposevideogamestfm.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevideogamestfm.GamesViewModel
import com.example.jetpackcomposevideogamestfm.ui.AppViewModelProvider
import com.example.jetpackcomposevideogamestfm.ui.GameEntryDeleteViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavDetailScreen(
    navController: NavController,
    id: String?,
    viewModel: GameEntryDeleteViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val gamesViewModel: GamesViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            DetailsTopBar(
                onClickIcon = {
                    navController.popBackStack()
                }
            )
        },
    ) {
        GetGameDetails(
            viewModel,
            gamesViewModel,
            id,
            navController,
            true
        )
    }

}