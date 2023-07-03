package com.hermes.marvelapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.hermes.marvelapp.data.local.CharacterEntity
import com.hermes.marvelapp.data.local.MarvelDatabase
import com.hermes.marvelapp.data.mappers.toCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    pager: Pager<Int, CharacterEntity>,
    private val marvelDatabase: MarvelDatabase
): ViewModel() {

    val characterPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toCharacter() }
        }
        .cachedIn(viewModelScope)

    val characterRetrieved = mutableStateOf<CharacterEntity?>(null)
    fun retrieveSingleCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            characterRetrieved.value = marvelDatabase.characterDao.retrieveCharacter(id)
        }
    }
}