package com.kwabenaberko.rickandmorty.data.db

import com.kwabenaberko.rickandmorty.data.db.model.DbCharacter
import com.kwabenaberko.rickandmorty.data.repository.datastore.DbCharacterDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DbCharacterDataStoreImpl @Inject constructor(
    private val database: RickAndMortyDatabase
): DbCharacterDataStore {
    override suspend fun findAllCharacters(): Flow<List<DbCharacter>> {
        return database.characterDao().findAllCharacters()
    }

    override suspend fun insertAllCharacters(dbCharacters: List<DbCharacter>) {
        database.characterDao().insertOrUpdateCharacters(*dbCharacters.toTypedArray())
    }
}