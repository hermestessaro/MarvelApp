package com.hermes.marvelapp.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
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
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null){
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }
            val characters = marvelApi.getAllCharacters(
                offset = (loadKey * state.config.pageSize)
            )

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