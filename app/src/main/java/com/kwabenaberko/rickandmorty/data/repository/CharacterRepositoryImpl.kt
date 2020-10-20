package com.kwabenaberko.rickandmorty.data.repository

import com.kwabenaberko.rickandmorty.data.db.DbCharacterDataMapper
import com.kwabenaberko.rickandmorty.data.db.model.DbCharacter
import com.kwabenaberko.rickandmorty.data.network.NetworkCharacterDataMapper
import com.kwabenaberko.rickandmorty.data.repository.datastore.DbCharacterDataStore
import com.kwabenaberko.rickandmorty.data.repository.datastore.NetworkCharacterDataStore
import com.kwabenaberko.rickandmorty.domain.CharacterRepository
import com.kwabenaberko.rickandmorty.domain.Result
import com.kwabenaberko.rickandmorty.domain.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val networkDataStore: NetworkCharacterDataStore,
    private val dbDataStore: DbCharacterDataStore,
    private val networkCharacterDataMapper: NetworkCharacterDataMapper,
    private val dbCharacterDataMapper: DbCharacterDataMapper
) : CharacterRepository {

    override suspend fun findAllCharacters(
        forceUpdate: Boolean
    ): Flow<Result<List<Character>, Exception>> = flow {

        getCharactersFromDB().collect { list ->

            emit(Result.Ok(list.map { dbCharacterDataMapper.mapFromData(it) }))


            if (list.isEmpty() || forceUpdate){
                withContext(Dispatchers.IO) {
                    val networkCharacters = networkDataStore.fetchAllCharacters(pageNumber = 1)
                    dbDataStore.insertAllCharacters(networkCharacters.map { networkCharacter ->
                        networkCharacterDataMapper.mapFromData(networkCharacter)
                    }.map { dbCharacterDataMapper.mapToData(it) })
                }

            }
        }


    }.flowOn(Dispatchers.Default)

    private suspend fun getCharactersFromDB(): Flow<List<DbCharacter>> {
        return dbDataStore.findAllCharacters()
    }
}