package com.hermes.marvelapp.repository

import androidx.paging.PagingData
import com.hermes.marvelapp.data.local.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacters(): Flow<PagingData<CharacterEntity>>
}