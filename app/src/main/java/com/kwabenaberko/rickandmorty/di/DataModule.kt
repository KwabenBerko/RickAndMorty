package com.kwabenaberko.rickandmorty.di

import com.kwabenaberko.rickandmorty.data.repository.CharacterRepositoryImpl
import com.kwabenaberko.rickandmorty.domain.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindCharacterRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

}