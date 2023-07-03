package com.hermes.marvelapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.hermes.marvelapp.data.local.ComicEntity
import com.hermes.marvelapp.data.mappers.toComic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    pager: Pager<Int, ComicEntity>
): ViewModel() {
    val comicPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toComic() }
        }
        .cachedIn(viewModelScope)
}