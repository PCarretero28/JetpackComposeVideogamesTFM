package com.example.jetpackcomposevideogamestfm.di

import com.example.jetpackcomposevideogamestfm.repository.GameRepository
import com.example.jetpackcomposevideogamestfm.repository.GameRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun gameRepository(repo: GameRepositoryImp) : GameRepository

}