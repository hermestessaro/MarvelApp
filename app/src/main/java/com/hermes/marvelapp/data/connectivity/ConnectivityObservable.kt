package com.hermes.marvelapp.data.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityObservable {
    fun observe(): Flow<Status>

    enum class Status {
        Available,
        Unavailable
    }
}