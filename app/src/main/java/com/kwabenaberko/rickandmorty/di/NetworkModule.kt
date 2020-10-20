package com.kwabenaberko.rickandmorty.di

import android.os.Build
import androidx.core.os.BuildCompat
import com.kwabenaberko.rickandmorty.BuildConfig
import com.kwabenaberko.rickandmorty.data.network.CharacterApi
import com.kwabenaberko.rickandmorty.data.network.NetworkCharacterDataStoreImpl
import com.kwabenaberko.rickandmorty.data.repository.datastore.NetworkCharacterDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class NetworkModule {

    companion object {

        @Singleton
        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor{
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            return interceptor
        }

        @Singleton
        @Provides
        fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }

        @Singleton
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

        @Singleton
        @Provides
        fun provideCharacterApi(retrofit: Retrofit): CharacterApi{
            return retrofit.create(CharacterApi::class.java)
        }

    }


    @Binds
    abstract fun bindNetworkCharacterDataStore(networkCharacterDataStoreImpl: NetworkCharacterDataStoreImpl): NetworkCharacterDataStore

}