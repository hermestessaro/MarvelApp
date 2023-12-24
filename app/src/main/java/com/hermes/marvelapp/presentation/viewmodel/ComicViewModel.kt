package com.hermes.marvelapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.hermes.marvelapp.data.connectivity.ConnectivityMonitor
import com.hermes.marvelapp.data.local.ComicEntity
import com.hermes.marvelapp.data.local.DatabaseRepository
import com.hermes.marvelapp.data.mappers.toComic
import com.hermes.marvelapp.repository.ComicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    repository: ComicRepository,
    private val dbRepository: DatabaseRepository,
    connectivityMonitor: ConnectivityMonitor
) : ViewModel() {
    val networkMonitor = connectivityMonitor
    val comicRetrieved = mutableStateOf<ComicEntity?>(null)
    val comicPagingFlow =
        repository.getAllComics().map { pagingData -> pagingData.map { it.toComic() } }
            .cachedIn(viewModelScope)

    fun retrieveSingleComic(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            comicRetrieved.value = dbRepository.comicDao().retrieveComic(id)
        }

    }
}