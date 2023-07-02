package com.hermes.marvelapp.data.mappers

import com.hermes.marvelapp.data.local.ComicEntity
import com.hermes.marvelapp.domain.Comic
import com.hermes.marvelapp.utils.charactersToString

fun Comic.toComicEntity(): ComicEntity {
    return ComicEntity(
        id = id,
        title = title,
        issueNumber = issueNumber,
        descripton = descripton,
        thumbnail = thumbnail?.path + "." + thumbnail?.extension,
        characters = characters?.items?.mapNotNull { it.name }?.charactersToString() ?: "No characters found"
    )
}