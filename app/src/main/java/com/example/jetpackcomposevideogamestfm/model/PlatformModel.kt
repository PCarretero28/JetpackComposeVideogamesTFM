package com.example.jetpackcomposevideogamestfm.model

import androidx.room.Entity

@Entity
data class PlatformModel(
    val platform: PlatformDetailsModel
)

@Entity
data class PlatformDetailsModel (
    val name: String
)
