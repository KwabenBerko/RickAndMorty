package com.kwabenaberko.rickandmorty.data.db

import com.kwabenaberko.rickandmorty.data.Mapper
import com.kwabenaberko.rickandmorty.data.db.model.DbCharacter
import com.kwabenaberko.rickandmorty.domain.model.Character
import javax.inject.Inject

class DbCharacterDataMapper @Inject constructor(): Mapper<DbCharacter, Character> {
    override fun mapFromData(data: DbCharacter): Character {
        return Character(
            id = data.id,
            name = data.name,
            imageUrl = data.imageUrl
        )
    }

    override fun mapToData(domain: Character): DbCharacter {
        return DbCharacter(
            id = domain.id,
            name = domain.name,
            imageUrl = domain.imageUrl
        )
    }
}