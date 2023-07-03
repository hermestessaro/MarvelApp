package com.hermes.marvelapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CharacterDao {
    @Upsert
    suspend fun upsertAllCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM CHARACTER_TABLE")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM CHARACTER_TABLE")
    suspend fun clearAll()

    @Query("SELECT * FROM CHARACTER_TABLE where id = :id")
    fun retrieveCharacter(id: Int): CharacterEntity
}