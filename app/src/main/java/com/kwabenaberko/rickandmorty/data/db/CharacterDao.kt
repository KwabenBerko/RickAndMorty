package com.kwabenaberko.rickandmorty.data.db

import androidx.room.*
import com.kwabenaberko.rickandmorty.data.db.model.DbCharacter
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CharacterDao {

    @Query("""
        SELECT * FROM characters
    """)
    abstract fun findAllCharacters(): Flow<List<DbCharacter>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    protected abstract fun insert(list: List<DbCharacter>): List<Long>

    @Update
    protected abstract fun update(list: List<DbCharacter>)

    @Transaction
    open suspend fun insertOrUpdateCharacters(vararg entities: DbCharacter) {
        val entitiesAsList = listOf(*entities)
        val entitiesToUpdate = mutableListOf<DbCharacter>()
        val ids = insert(entitiesAsList)

        for (i in ids.indices) {
            if (ids[i] == -1L) {
                entitiesToUpdate += entitiesAsList[i]
            }
        }
        if (entitiesToUpdate.isNotEmpty()) {
            update(entitiesToUpdate)
        }
    }

}