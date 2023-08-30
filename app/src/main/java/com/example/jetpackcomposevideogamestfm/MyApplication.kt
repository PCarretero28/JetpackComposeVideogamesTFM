package com.example.jetpackcomposevideogamestfm

import android.app.Application
import com.example.jetpackcomposevideogamestfm.data.AppContainer
import com.example.jetpackcomposevideogamestfm.data.AppDataContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application(){

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}