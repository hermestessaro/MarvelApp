package com.hermes.marvelapp.navigation

sealed class Destination(val route: String) {
    object CharacterList: Destination("CharacterList")
    object Bookmarked: Destination("Bookmarked")
    object CharacterDetail: Destination("character/{characterId}") {
        fun createRoute(characterId: Int?): String {
            return "character/$characterId"
        }
    }
}