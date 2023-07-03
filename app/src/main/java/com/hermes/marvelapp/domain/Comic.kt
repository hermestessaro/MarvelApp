package com.hermes.marvelapp.domain

data class ComicApiResponse(
    val code: String?,
    val status: String?,
    val attributionText: String?,
    val data: ComicData
)

data class ComicData(
    val total: Int,
    val results: List<Comic>
)

data class Comic(
    val id: Int,
    val title: String,
    val issueNumber: Double,
    val descripton: String?,
    val thumbnail: ComicThumbnail,
    val characters: ComicCharacters
)

data class  ComicThumbnail(
    val path: String,
    val extension: String
) {
    val pathSecure: String
        get() = path.replace("http:", "https:")
}

data class ComicCharacters(
    val items: List<ComicCharactersItem>?
)

data class ComicCharactersItem(
    val name: String?
)