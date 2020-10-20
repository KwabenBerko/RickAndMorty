package com.kwabenaberko.rickandmorty.presentation.characters

import com.kwabenaberko.rickandmorty.domain.model.Character

data class CharactersViewState(
    val isLoading: Boolean = false,
    val characters: List<Character> = listOf()
)
