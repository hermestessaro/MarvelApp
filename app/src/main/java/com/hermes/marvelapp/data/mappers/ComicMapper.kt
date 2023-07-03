package com.hermes.marvelapp.data.mappers

import com.hermes.marvelapp.data.local.ComicEntity
import com.hermes.marvelapp.domain.Comic
import com.hermes.marvelapp.domain.ComicCharacters
import com.hermes.marvelapp.domain.ComicCharactersItem
import com.hermes.marvelapp.domain.ComicThumbnail
import com.hermes.marvelapp.utils.charactersToString

fun Comic.toComicEntity(): ComicEntity {
    return ComicEntity(
        id = 0,
        apiId = id,
        title = title,
        issueNumber = issueNumber,
        descripton = descripton,
        path = thumbnail.pathSecure,
        extension = thumbnail.extension,
        characters = characters.items?.mapNotNull { it.name }?.charactersToString() ?: "No characters found"
    )
}

fun ComicEntity.toComic(): Comic {
    return Comic(
        id = id,
        title = title,
        issueNumber = issueNumber,
        descripton = descripton,
        thumbnail = ComicThumbnail(path, extension),
        characters = ComicCharacters(characters.split(", ").map{ ComicCharactersItem(it) })
    )
}