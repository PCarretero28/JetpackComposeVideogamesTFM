package com.example.jetpackcomposevideogamestfm.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.jetpackcomposevideogamestfm.MyApplication


object AppViewModelProvider {
    val Factory = viewModelFactory {

        // Initializer for GameEntryViewModel
        initializer {
            GameEntryViewModel(inventoryApplication().container.gamesRepository)
        }

        // Initializer for HomeViewModel
        initializer {
            FavGamesViewModel(inventoryApplication().container.gamesRepository)
        }

    }
}

fun CreationExtras.inventoryApplication(): MyApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)