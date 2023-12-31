package com.hermes.marvelapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.hermes.marvelapp.presentation.navigation.BottomNav
import com.hermes.marvelapp.presentation.navigation.NavGraph
import com.hermes.marvelapp.presentation.viewmodel.CharacterViewModel
import com.hermes.marvelapp.presentation.viewmodel.ComicViewModel
import com.hermes.marvelapp.ui.theme.MarvelAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            MarvelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = { BottomNav(navController = navController) },
                        content = {
                            val characterViewModel = hiltViewModel<CharacterViewModel>()
                            val comicViewModel = hiltViewModel<ComicViewModel>()
                            NavGraph(navController = navController, characterViewModel, comicViewModel)
                        }
                    )
                }
            }
        }
    }
}