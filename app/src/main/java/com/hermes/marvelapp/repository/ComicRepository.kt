package com.hermes.marvelapp.repository

import androidx.paging.PagingData
import com.hermes.marvelapp.data.local.ComicEntity
import kotlinx.coroutines.flow.Flow

interface ComicRepository {
    fun getAllComics(): Flow<PagingData<ComicEntity>>
}