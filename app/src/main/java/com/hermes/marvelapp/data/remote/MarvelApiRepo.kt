package com.hermes.marvelapp.data.remote

import androidx.compose.runtime.mutableStateOf
import com.hermes.marvelapp.domain.CharactersApiResponse
import com.hermes.marvelapp.domain.Character
import com.hermes.marvelapp.domain.Comic
import com.hermes.marvelapp.domain.ComicApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarvelApiRepo(private val api: MarvelApi) {
    val characters = MutableStateFlow<NetworkResult<CharactersApiResponse>>(NetworkResult.Initial())
    val characterDetails = mutableStateOf<Character?>(null)
    val comics = MutableStateFlow<NetworkResult<ComicApiResponse>>(NetworkResult.Initial())
    val comicDetails = mutableStateOf<Comic?>(null)

    fun getCharacters(query:String?) {
        characters.value = NetworkResult.Loading()
        api.getCharacters(query)
            .enqueue(object: Callback<CharactersApiResponse> {
                override fun onResponse(
                    call: Call<CharactersApiResponse>,
                    response: Response<CharactersApiResponse>
                ) {
                    if(response.isSuccessful) {
                        response.body()?.let {
                            characters.value = NetworkResult.Success(it)
                        }
                    } else {
                        characters.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<CharactersApiResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        characters.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            })
    }

    fun getComics(query: String) {
        comics.value = NetworkResult.Loading()
        api.getComics(query)
            .enqueue(object: Callback<ComicApiResponse> {
                override fun onResponse(
                    call: Call<ComicApiResponse>,
                    response: Response<ComicApiResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let{
                            comics.value = NetworkResult.Success(it)
                        }
                    } else {
                        comics.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ComicApiResponse>, t: Throwable) {
                    t.localizedMessage?.let{
                        comics.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }

            })
    }

    fun getSingleCharacter(id: Int?) {
        id?.let{
            characterDetails.value = characters.value.data?.data?.results?.firstOrNull { character ->
                character.id == id
            }
        }
    }
}