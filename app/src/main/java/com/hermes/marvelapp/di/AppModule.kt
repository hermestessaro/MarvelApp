package com.hermes.marvelapp.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.hermes.marvelapp.data.local.CharacterEntity
import com.hermes.marvelapp.data.local.ComicEntity
import com.hermes.marvelapp.data.local.MarvelDatabase
import com.hermes.marvelapp.data.remote.ApiService
import com.hermes.marvelapp.data.remote.CharacterRemoteMediator
import com.hermes.marvelapp.data.remote.ComicRemoteMediator
import com.hermes.marvelapp.data.remote.MarvelApi
import com.hermes.marvelapp.utils.Constants.PAGE_SIZE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
    fun provideMarvelApi(): MarvelApi {
        return ApiService.api
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideCharacterPager(marvelDatabase: MarvelDatabase, marvelApi: MarvelApi): Pager<Int, CharacterEntity> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            remoteMediator = CharacterRemoteMediator(
                marvelDatabase,
                marvelApi
            ),
            pagingSourceFactory = {
                marvelDatabase.characterDao.pagingSource()
            }
        )
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideComicsPager(marvelDatabase: MarvelDatabase, marvelApi: MarvelApi): Pager<Int, ComicEntity> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            remoteMediator = ComicRemoteMediator(
                marvelDatabase,
                marvelApi
            ),
            pagingSourceFactory = {
                marvelDatabase.comicDao.pagingSource()
            }
        )
    }
}