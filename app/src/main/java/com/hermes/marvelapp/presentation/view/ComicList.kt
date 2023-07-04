package com.hermes.marvelapp.presentation.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.hermes.marvelapp.data.connectivity.ConnectivityMonitor
import com.hermes.marvelapp.data.connectivity.ConnectivityObservable
import com.hermes.marvelapp.domain.Comic
import com.hermes.marvelapp.presentation.navigation.Destination

@Composable
fun ComicList(
    comics: LazyPagingItems<Comic>,
    navHostController: NavHostController,
    networkMonitor: ConnectivityMonitor
) {
    val networkStatus =
        networkMonitor.observe().collectAsState(ConnectivityObservable.Status.Available)
    val context = LocalContext.current

    LaunchedEffect(key1 = comics.loadState) {
        if (comics.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "An error occurred! Please try again later.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {
        var listPaddingTop = 0.dp
        if (networkStatus.value == ConnectivityObservable.Status.Unavailable) {
            listPaddingTop = 40.dp
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .height(24.dp)
            ) {
                Icon(imageVector = Icons.Default.Warning, contentDescription = null)
                Text(
                    text = "Sorry, no internet connection",
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        if (comics.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.TopCenter)
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 8.dp,
                    end = 8.dp,
                    top = listPaddingTop,
                    bottom = 100.dp
                ),
            ) {
                items(comics.itemCount) { index ->
                    val item = comics[index]
                    if (item != null) {
                        val modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                            .fillMaxWidth()
                            .clickable {
                                navHostController.navigate(Destination.ComicDetail.createRoute(item.id))
                            }
                        ComicItem(comic = item, modifier)
                    }
                }
                item {
                    if (comics.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }
            }
        }
    }
}