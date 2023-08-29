package com.example.jetpackcomposevideogamestfm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game (
    @PrimaryKey
    val id: Int,
    val name: String,
    val description_raw: String,
    val metacritic: Int,
    val released: String,
    val background_image: String,
    val background_image_additional: String,
    val reviews_count: Int
)