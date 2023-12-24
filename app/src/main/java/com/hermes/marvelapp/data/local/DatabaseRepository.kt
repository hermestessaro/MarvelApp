package com.hermes.marvelapp.data.local

import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val database: MarvelDatabase
) {
    fun characterDao(): CharacterDao = database.characterDao
    fun comicDao(): ComicDao = database.comicDao
}