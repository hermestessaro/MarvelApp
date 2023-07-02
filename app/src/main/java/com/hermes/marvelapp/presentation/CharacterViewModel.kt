package com.hermes.marvelapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.hermes.marvelapp.data.local.CharacterEntity
import com.hermes.marvelapp.data.mappers.toCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    pager: Pager<Int, CharacterEntity>
): ViewModel() {

    val characterPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toCharacter() }
        }
        .cachedIn(viewModelScope)
}