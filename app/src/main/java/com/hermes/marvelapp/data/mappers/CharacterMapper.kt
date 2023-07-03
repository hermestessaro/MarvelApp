package com.hermes.marvelapp.data.mappers

import com.hermes.marvelapp.data.local.CharacterEntity
import com.hermes.marvelapp.domain.Character
import com.hermes.marvelapp.domain.CharacterComics
import com.hermes.marvelapp.domain.CharacterComicsItems
import com.hermes.marvelapp.domain.CharacterThumbnail
import com.hermes.marvelapp.utils.comicsToString

fun Character.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = 0,
        apiId = id,
        name = name,
        description = description,
        path = thumbnail.pathSecure,
        extension = thumbnail.extension,
        comics = comics.items?.mapNotNull { it.name }?.comicsToString() ?: "No comics found"
    )
}

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        description = description,
        thumbnail = CharacterThumbnail(path, extension),
        comics = CharacterComics(comics.split(", ").map { CharacterComicsItems(it) })
    )
}