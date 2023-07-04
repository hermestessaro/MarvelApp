package com.hermes.marvelapp.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.hermes.marvelapp.domain.Comic
import com.hermes.marvelapp.presentation.navigation.Destination

@Composable
fun ComicList(
    comics: LazyPagingItems<Comic>,
    navHostController: NavHostController
) {
    Box(modifier = Modifier.fillMaxSize()){
        if (comics.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 30.dp, bottom = 100.dp),
            ) {
                items(comics.itemCount) { index ->
                    val item = comics[index]
                    if(item != null) {
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
                    if(comics.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }
            }
        }
    }
}