package com.hermes.marvelapp.navigation

sealed class Destination(val route: String) {
    object CharacterDetail: Destination("character/{characterId}") {
        fun createRoute(characterId: Int?): String {
            return "character/$characterId"
        }
    }
    object ComicDetail: Destination("comic/{comicId}") {
        fun createRoute(comicId: Int?): String {
            return "comic/$comicId"
        }
    }
}