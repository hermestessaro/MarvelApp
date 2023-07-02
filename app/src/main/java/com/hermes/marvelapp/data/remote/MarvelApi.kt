package com.hermes.marvelapp.data.remote

import com.hermes.marvelapp.domain.CharactersApiResponse
import com.hermes.marvelapp.domain.ComicApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("characters")
    fun getCharacters(@Query("nameStartsWith") name: String?): Call<CharactersApiResponse>

    @GET("comics")
    fun getComics(@Query("titleStartsWith") title: String?): Call<ComicApiResponse>

}