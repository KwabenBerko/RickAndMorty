package com.kwabenaberko.rickandmorty.data.repository.datastore

import com.kwabenaberko.rickandmorty.data.network.model.NetworkCharacter

interface NetworkCharacterDataStore {

    suspend fun fetchAllCharacters(): List<NetworkCharacter>

}