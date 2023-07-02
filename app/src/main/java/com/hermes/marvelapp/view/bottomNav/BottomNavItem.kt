package com.hermes.marvelapp.view.bottomNav

import com.hermes.marvelapp.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    var route: String
) {
    object CharacterList: BottomNavItem("Characters", R.drawable.ic_superhero, "CharacterList")
    object ComicList: BottomNavItem("Comic Books", R.drawable.ic_comic_book, "ComicList")
    /*object CharacterDetail: BottomNavItem("Character Detail", null, "character/{characterId}") {
        fun createRoute(characterId: Int?): String {
            return "character/$characterId"
        }
    }

    object ComicDetail: BottomNavItem("Comic Detail", null, "comic/{comicId}") {
        fun createRoute(comicId: Int?): String {
            return "comic/$comicId"
        }
    }*/
}