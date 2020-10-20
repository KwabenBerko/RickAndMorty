package com.kwabenaberko.rickandmorty.data.network

import com.kwabenaberko.rickandmorty.data.Mapper
import com.kwabenaberko.rickandmorty.data.network.model.NetworkCharacter
import com.kwabenaberko.rickandmorty.domain.model.Character
import javax.inject.Inject

class NetworkCharacterDataMapper @Inject constructor(): Mapper<NetworkCharacter, Character> {
    override fun mapFromData(data: NetworkCharacter): Character {
        return Character(
            id = data.id,
            name = data.name,
            imageUrl = data.image
        )
    }

    override fun mapToData(domain: Character): NetworkCharacter {
        TODO("Not yet implemented")
    }
}