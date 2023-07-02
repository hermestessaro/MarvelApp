package com.hermes.marvelapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.hermes.marvelapp.presentation.CharacterViewModel
import com.hermes.marvelapp.presentation.view.CharactersList
import com.hermes.marvelapp.presentation.view.ComicList

@Composable
fun NavGraph(navController: NavHostController, characterViewModel: CharacterViewModel) {
    NavHost(navController = navController, startDestination = BottomNavItem.CharacterList.route, builder = {
        composable(route = BottomNavItem.CharacterList.route){
            CharactersList(characterViewModel.characterPagingFlow.collectAsLazyPagingItems())
        }
        composable(route = BottomNavItem.ComicList.route){
            ComicList()
        }
    })
}