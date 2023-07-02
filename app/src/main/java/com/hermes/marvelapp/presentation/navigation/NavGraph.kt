package com.hermes.marvelapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hermes.marvelapp.presentation.view.CharactersList
import com.hermes.marvelapp.presentation.view.ComicList

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = BottomNavItem.CharacterList.route, builder = {
        composable(route = BottomNavItem.CharacterList.route){
            CharactersList()
        }
        composable(route = BottomNavItem.ComicList.route){
            ComicList()
        }
    })
}