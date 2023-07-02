package com.hermes.marvelapp.presentation.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNav(navController: NavController) {
    val items = listOf(
        BottomNavItem.CharacterList,
        BottomNavItem.ComicList
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach{ item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title, modifier = Modifier.size(24.dp)) },
                label = { Text(text = item.title)},
                selected = currentRoute == item.route,
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.route){
                       navController.graph.startDestinationRoute?.let{ route ->
                           popUpTo(route) {
                               saveState = true
                           }
                       }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}