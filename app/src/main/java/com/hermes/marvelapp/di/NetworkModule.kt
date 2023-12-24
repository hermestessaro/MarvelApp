package com.hermes.marvelapp.di

import android.content.Context
import com.hermes.marvelapp.data.connectivity.ConnectivityMonitor
import com.hermes.marvelapp.data.local.MarvelDatabase
import com.hermes.marvelapp.data.remote.ApiService
import com.hermes.marvelapp.data.remote.MarvelApi
import com.hermes.marvelapp.repository.CharacterRepository
import com.hermes.marvelapp.repository.CharacterRepositoryImpl
import com.hermes.marvelapp.repository.ComicRepository
import com.hermes.marvelapp.repository.ComicRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMarvelApi(): MarvelApi {
        return ApiService.api
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityMonitor {
        return ConnectivityMonitor.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideComicRepository(
        marvelApi: MarvelApi,
        marvelDatabase: MarvelDatabase
    ): ComicRepository {
        return ComicRepositoryImpl(marvelApi, marvelDatabase)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(
        marvelApi: MarvelApi,
        marvelDatabase: MarvelDatabase
    ): CharacterRepository {
        return CharacterRepositoryImpl(marvelApi, marvelDatabase)
    }
}