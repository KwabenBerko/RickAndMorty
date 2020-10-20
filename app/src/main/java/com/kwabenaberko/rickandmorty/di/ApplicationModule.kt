package com.kwabenaberko.rickandmorty.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class ApplicationModule {

    companion object {
        @Singleton
        @Provides
        fun provideContext(@ApplicationContext context: Context): Context {
            return context
        }
    }

}