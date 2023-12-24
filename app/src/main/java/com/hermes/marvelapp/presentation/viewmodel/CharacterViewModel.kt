package com.hermes.marvelapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.hermes.marvelapp.data.connectivity.ConnectivityMonitor
import com.hermes.marvelapp.data.local.CharacterEntity
import com.hermes.marvelapp.data.local.DatabaseRepository
import com.hermes.marvelapp.data.mappers.toCharacter
import com.hermes.marvelapp.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    repository: CharacterRepository,
    private val dbRepository: DatabaseRepository,
    connectivityMonitor: ConnectivityMonitor
) : ViewModel() {
    val networkMonitor = connectivityMonitor
    val characterRetrieved = mutableStateOf<CharacterEntity?>(null)
    val characterPagingFlow =
        repository.getAllCharacters().map { pagingData -> pagingData.map { it.toCharacter() } }
            .cachedIn(viewModelScope)


    fun retrieveSingleCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            characterRetrieved.value = dbRepository.characterDao().retrieveCharacter(id)
        }
    }
}