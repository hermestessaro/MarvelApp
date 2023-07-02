package com.hermes.marvelapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hermes.marvelapp.utils.Constants.CHARACTER_TABLE

@Entity(tableName = CHARACTER_TABLE)
data class CharacterEntity(
        @PrimaryKey
        val id: Int,
        val name: String,
        val description: String,
        val path: String,
        val extension: String,
        val comics: String
)