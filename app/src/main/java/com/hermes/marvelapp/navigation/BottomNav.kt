package com.hermes.marvelapp.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hermes.marvelapp.R
import com.hermes.marvelapp.ui.theme.MarvelAppTheme
import com.hermes.marvelapp.view.CharactersList
import com.hermes.marvelapp.view.bottomNav.BottomNavItem

@Composable
fun BottomNav(navController: NavController) {
    val items = listOf(
        BottomNavItem.CharacterList,
        BottomNavItem.ComicList
    )
    NavigationBar(
        containerColor = Color(R.color.purple_200),
        contentColor = Color(R.color.black)
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