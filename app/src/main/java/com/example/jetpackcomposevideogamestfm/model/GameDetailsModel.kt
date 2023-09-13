package com.example.jetpackcomposevideogamestfm.model

data class GameDetailsModel(
    val id: Int,
    val name: String,
    val description_raw: String,
    val metacritic: Int,
    val released: String,
    val background_image: String,
    val background_image_additional: String,
    val reviews_count: Int,
    val platforms: List<PlatformModel>,
    val developers: List<DeveloperModel>,
    val genres: List<GenreModel>
)
