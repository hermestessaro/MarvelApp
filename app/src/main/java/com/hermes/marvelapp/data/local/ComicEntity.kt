package com.hermes.marvelapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hermes.marvelapp.utils.Constants.COMICS_TABLE

@Entity(tableName = COMICS_TABLE)
data class ComicEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val apiId: Int,
    val title: String,
    val issueNumber: Double,
    val descripton: String?,
    val path: String,
    val extension: String,
    val characters: String?
)