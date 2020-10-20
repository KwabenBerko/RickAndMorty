package com.kwabenaberko.rickandmorty.domain

import com.kwabenaberko.rickandmorty.domain.model.Character
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

interface CharacterRepository {

    suspend fun findAllCharacters(
        forceUpdate: Boolean = false
    ): Flow<Result<List<Character>, Exception>>

}