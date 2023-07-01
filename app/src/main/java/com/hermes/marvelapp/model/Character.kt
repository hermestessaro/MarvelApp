package com.hermes.marvelapp.model

data class CharactersApiResponse(
    val code: String?,
    val status: String?,
    val attributionText: String?,
    val data: CharactersData?
)

data class CharactersData(
    val total: Int?,
    val results: List<Character>?
)

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<CharacterUrl>?,
    val thumbnail: CharacterThumbnail?,
    val comics: CharacterComics?
)

data class CharacterUrl(
    val type: String?,
    val url: String?
)

data class CharacterThumbnail(
    val path: String?,
    val extension: String?
)

data class CharacterComics(
    val items: List<CharacterComicsItems>?
)

data class CharacterComicsItems(
    val resourceURI: String?,
    val name: String?
)