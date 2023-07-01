package com.hermes.marvelapp.model

data class ComicApiResponse(
    val code: String?,
    val status: String?,
    val attributionText: String?,
    val data: ComicData?
)

data class ComicData(
    val total: Int?,
    val results: List<Comic>
)

data class Comic(
    val id: Int?,
    val title: String?,
    val issueNumber: Double?,
    val descripton: String?,
    val urls: List<ComicUrl>,
    val thumbnail: ComicThumbnail,
    val characters: List<ComicCharacters>
)

data class ComicUrl(
    val type: String?,
    val url: String?
)

data class  ComicThumbnail(
    val path: String?,
    val extension: String?
)

data class ComicCharacters(
    val items: List<ComicCharactersItem>?
)

data class ComicCharactersItem(
    val resourceURI: String?,
    val name: String?
)