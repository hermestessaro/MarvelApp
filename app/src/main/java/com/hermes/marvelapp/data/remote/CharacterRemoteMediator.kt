package com.hermes.marvelapp.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.paging.util.getClippedRefreshKey
import androidx.room.withTransaction
import com.hermes.marvelapp.data.local.CharacterEntity
import com.hermes.marvelapp.data.local.MarvelDatabase
import com.hermes.marvelapp.data.mappers.toCharacterEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val marvelDatabase: MarvelDatabase,
    private val marvelApi: MarvelApi
) : RemoteMediator<Int, CharacterEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, CharacterEntity>): MediatorResult {
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
            val characters = marvelApi.getAllCharacters(loadKey)

            marvelDatabase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    marvelDatabase.characterDao.clearAll()
                }
                val characterEntities = characters.data.results.map { it.toCharacterEntity() }
                marvelDatabase.characterDao.upsertAllCharacters(characterEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = characters.data.results.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}