package com.hermes.marvelapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterEntity::class, ComicEntity::class],
    version = 1
)
abstract class MarvelDatabase: RoomDatabase() {

    abstract val characterDao: CharacterDao
    abstract val comicDao: ComicDao
}