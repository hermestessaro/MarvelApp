package com.hermes.marvelapp.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hermes.marvelapp.data.local.ComicEntity
import com.hermes.marvelapp.data.local.MarvelDatabase
import com.hermes.marvelapp.data.remote.CharacterRemoteMediator
import com.hermes.marvelapp.data.remote.ComicRemoteMediator
import com.hermes.marvelapp.data.remote.MarvelApi
import com.hermes.marvelapp.domain.Comic
import com.hermes.marvelapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ComicRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi,
    private val marvelDatabase: MarvelDatabase
): ComicRepository {
    override fun getAllComics(): Flow<PagingData<ComicEntity>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE),
            remoteMediator = ComicRemoteMediator(
                marvelDatabase,
                marvelApi
            ),
            pagingSourceFactory = {
                marvelDatabase.comicDao.pagingSource()
            }
        ).flow
    }
}