package com.hermes.marvelapp.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hermes.marvelapp.data.local.ComicEntity
import com.hermes.marvelapp.data.local.MarvelDatabase
import com.hermes.marvelapp.data.mappers.toComicEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ComicRemoteMediator(
    private val marvelDatabase: MarvelDatabase,
    private val marvelApi: MarvelApi
): RemoteMediator<Int, ComicEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ComicEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.let {
                        it.id + 1
                    } ?: 0
                }
            }
            val comics = marvelApi.getAllComics(loadKey)

            marvelDatabase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    marvelDatabase.comicDao.clearAll()
                }
                val comicEntities = comics.data.results.map { it.toComicEntity() }
                marvelDatabase.comicDao.upsertAllComics(comicEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = comics.data.results.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}