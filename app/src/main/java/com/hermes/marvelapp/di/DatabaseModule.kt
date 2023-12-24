package com.hermes.marvelapp.di

import android.content.Context
import androidx.room.Room
import com.hermes.marvelapp.data.local.DatabaseRepository
import com.hermes.marvelapp.data.local.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMarvelDatabase(@ApplicationContext context: Context): MarvelDatabase {
        return Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            "marvel.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(
        marvelDatabase: MarvelDatabase
    ): DatabaseRepository {
        return DatabaseRepository(marvelDatabase)
    }
}