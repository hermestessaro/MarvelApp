package com.hermes.marvelapp.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hermes.marvelapp.domain.Character
import com.hermes.marvelapp.domain.CharacterComics
import com.hermes.marvelapp.domain.CharacterComicsItems
import com.hermes.marvelapp.domain.CharacterThumbnail

@Composable
fun CharacterItem(character: Character, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                        .height(IntrinsicSize.Max)
        ) {
            AsyncImage(
                model = character.thumbnail.pathSecure + "." + character.thumbnail.extension,
                contentDescription = character.name,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .weight(1f)
                    .defaultMinSize(minHeight = 120.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = character.name)
                Text(text = "Click to view character details")
            }
        }
    }
}