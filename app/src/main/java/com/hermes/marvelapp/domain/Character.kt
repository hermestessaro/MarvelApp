package com.hermes.marvelapp.domain

data class CharactersApiResponse(
    val code: String?,
    val status: String?,
    val attributionText: String?,
    val data: CharactersData
)

data class CharactersData(
    val total: Int,
    val results: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: CharacterThumbnail,
    val comics: CharacterComics
)

data class CharacterThumbnail(
    val path: String,
    val extension: String
) {
    val pathSecure: String
        get() = path.replace("http:", "https:")
}

data class CharacterComics(
    val items: List<CharacterComicsItems>?
)

data class CharacterComicsItems(
    val name: String?
)