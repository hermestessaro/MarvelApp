package com.hermes.marvelapp.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hermes.marvelapp.data.local.CharacterEntity
import com.hermes.marvelapp.data.local.MarvelDatabase
import com.hermes.marvelapp.data.remote.CharacterRemoteMediator
import com.hermes.marvelapp.data.remote.MarvelApi
import com.hermes.marvelapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharacterRepositoryImpl@Inject constructor(
    private val marvelApi: MarvelApi,
    private val marvelDatabase: MarvelDatabase
): CharacterRepository {
    override fun getAllCharacters(): Flow<PagingData<CharacterEntity>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE),
            remoteMediator = CharacterRemoteMediator(
                marvelDatabase,
                marvelApi
            ),
            pagingSourceFactory = {
                marvelDatabase.characterDao.pagingSource()
            }
        ).flow
    }
}