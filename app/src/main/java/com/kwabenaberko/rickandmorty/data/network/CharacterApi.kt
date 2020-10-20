package com.kwabenaberko.rickandmorty.data.network

import com.kwabenaberko.rickandmorty.data.network.model.GetCharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("/api/character")
    suspend fun fetchCharacters(@Query("page") pageNumber: Int): GetCharacterResponse

}