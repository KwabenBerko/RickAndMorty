package com.kwabenaberko.rickandmorty.data.network

import com.kwabenaberko.rickandmorty.data.network.model.NetworkCharacter
import com.kwabenaberko.rickandmorty.data.repository.datastore.NetworkCharacterDataStore
import javax.inject.Inject

class NetworkCharacterDataStoreImpl @Inject constructor(
    private val characterApi: CharacterApi
): NetworkCharacterDataStore {
    override suspend fun fetchAllCharacters(pageNumber: Int): List<NetworkCharacter> {
        return characterApi.fetchCharacters(pageNumber).results
    }
}