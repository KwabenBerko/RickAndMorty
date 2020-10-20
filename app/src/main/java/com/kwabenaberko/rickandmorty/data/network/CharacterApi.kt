package com.kwabenaberko.rickandmorty.data.network

import com.kwabenaberko.rickandmorty.data.network.model.GetCharacterResponse
import retrofit2.http.GET

interface CharacterApi {

    @GET("/api/character")
    suspend fun fetchCharacters(): GetCharacterResponse

}