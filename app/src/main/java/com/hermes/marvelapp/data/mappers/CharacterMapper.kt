package com.hermes.marvelapp.data.mappers

import com.hermes.marvelapp.data.local.CharacterEntity
import com.hermes.marvelapp.domain.Character
import com.hermes.marvelapp.utils.comicsToString

fun Character.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        description = description,
        thumbnail = thumbnail?.path + "." + thumbnail?.extension,
        comics = comics?.items?.mapNotNull { it.name }?.comicsToString() ?: "No comics found"
    )
}