package com.hermes.marvelapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
        @PrimaryKey
        val id: Int,
        val name: String,
        val description: String?,
        val thumbnail: String?,
        val comics: String?
)