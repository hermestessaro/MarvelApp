package com.hermes.marvelapp.data.remote

import com.hermes.marvelapp.domain.CharactersApiResponse
import com.hermes.marvelapp.domain.ComicApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("characters")
    suspend fun getAllCharacters(@Query("offset") offset: Int?): CharactersApiResponse

    @GET("comics")
    suspend fun getAllComics(@Query("offset") offset: Int?): ComicApiResponse

}