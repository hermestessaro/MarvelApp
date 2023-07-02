package com.hermes.marvelapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ComicEntity(
    @PrimaryKey
    val id: Int,
    val title: String?,
    val issueNumber: Double?,
    val descripton: String?,
    val thumbnail: String?,
    val characters: String?
)