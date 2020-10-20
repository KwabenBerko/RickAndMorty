package com.kwabenaberko.rickandmorty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kwabenaberko.rickandmorty.data.db.model.DbCharacter

@Database(
    entities = [DbCharacter::class],
    version = 1,
    exportSchema = false
)
abstract class RickAndMortyDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao

}