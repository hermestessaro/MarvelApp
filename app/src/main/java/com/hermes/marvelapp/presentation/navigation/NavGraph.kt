package com.hermes.marvelapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.hermes.marvelapp.presentation.view.CharacterDetail
import com.hermes.marvelapp.presentation.viewmodel.CharacterViewModel
import com.hermes.marvelapp.presentation.view.CharactersList
import com.hermes.marvelapp.presentation.view.ComicDetail
import com.hermes.marvelapp.presentation.view.ComicList
import com.hermes.marvelapp.presentation.viewmodel.ComicViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    characterViewModel: CharacterViewModel,
    comicViewModel: ComicViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.CharacterList.route,
        builder = {
            composable(route = BottomNavItem.CharacterList.route) {
                CharactersList(
                    characterViewModel.characterPagingFlow.collectAsLazyPagingItems(),
                    navController
                )
            }
            composable(route = BottomNavItem.ComicList.route) {
                ComicList(
                    comicViewModel.comicPagingFlow.collectAsLazyPagingItems(),
                    navController)
            }
            composable(route = Destination.CharacterDetail.route) { navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getString("characterId")?.toIntOrNull()
                if(id == null) {
                    /*TODO*/
                } else {
                    characterViewModel.retrieveSingleCharacter(id)
                    characterViewModel.characterRetrieved.value?.let {
                        CharacterDetail(singleCharacter = it)
                    } ?: {
                        /*TODO*/
                    }
                }
            }
            composable(route = Destination.ComicDetail.route) { navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getString("comicId")?.toIntOrNull()
                if(id == null) {
                    /*TODO*/
                } else {
                    comicViewModel.retrieveSingleComic(id)
                    comicViewModel.comicRetrieved.value?.let{
                        ComicDetail(singleComic = it)
                    } ?: {
                        /*TODO*/
                    }
                }
            }
        })
}