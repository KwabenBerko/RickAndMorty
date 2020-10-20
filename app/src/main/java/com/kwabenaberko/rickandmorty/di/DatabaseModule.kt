package com.kwabenaberko.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.kwabenaberko.rickandmorty.data.db.DbCharacterDataStoreImpl
import com.kwabenaberko.rickandmorty.data.db.RickAndMortyDatabase
import com.kwabenaberko.rickandmorty.data.repository.datastore.DbCharacterDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module(includes = [ApplicationModule::class])
abstract class DatabaseModule {

    companion object{
        @Singleton
        @Provides
        fun provideRickAndMortyDatabase(context: Context): RickAndMortyDatabase{
            return Room.databaseBuilder(context, RickAndMortyDatabase::class.java, "rickandmorty.db")
                .allowMainThreadQueries()
                .build()
        }
    }

    @Binds
    abstract fun bindDbCharacterDataStore(dbCharacterDataStoreImpl: DbCharacterDataStoreImpl): DbCharacterDataStore

}