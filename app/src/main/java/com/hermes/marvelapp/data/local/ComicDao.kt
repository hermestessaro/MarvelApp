package com.hermes.marvelapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ComicDao {
    @Upsert
    suspend fun upsertAllComics(comics: List<ComicEntity>)

    @Query("SELECT * FROM COMICS_TABLE")
    fun pagingSource(): PagingSource<Int, ComicEntity>

    @Query("DELETE FROM COMICS_TABLE")
    suspend fun clearAll()

    @Query("SELECT * FROM COMICS_TABLE where id = :id")
    fun retrieveComic(id: Int): ComicEntity
}