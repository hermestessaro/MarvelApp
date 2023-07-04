package com.hermes.marvelapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.hermes.marvelapp.data.connectivity.ConnectivityMonitor
import com.hermes.marvelapp.data.local.ComicEntity
import com.hermes.marvelapp.data.local.MarvelDatabase
import com.hermes.marvelapp.data.mappers.toComic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    pager: Pager<Int, ComicEntity>,
    private val marvelDatabase: MarvelDatabase,
    connectivityMonitor: ConnectivityMonitor
): ViewModel() {
    val networkMonitor = connectivityMonitor
    val comicPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toComic() }
        }
        .cachedIn(viewModelScope)

    val comicRetrieved = mutableStateOf<ComicEntity?>(null)
    fun retrieveSingleComic(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            comicRetrieved.value = marvelDatabase.comicDao.retrieveComic(id)
        }

    }
}