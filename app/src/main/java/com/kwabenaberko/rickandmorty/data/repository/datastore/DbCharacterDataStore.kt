package com.kwabenaberko.rickandmorty.data.repository.datastore

import com.kwabenaberko.rickandmorty.data.db.model.DbCharacter
import kotlinx.coroutines.flow.Flow

interface DbCharacterDataStore {

    suspend fun findAllCharacters(): Flow<List<DbCharacter>>

    suspend fun insertAllCharacters(dbCharacters: List<DbCharacter>)

}