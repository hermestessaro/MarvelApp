package com.hermes.marvelapp.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hermes.marvelapp.domain.Character
import com.hermes.marvelapp.domain.CharacterComics
import com.hermes.marvelapp.domain.CharacterComicsItems
import com.hermes.marvelapp.domain.CharacterThumbnail

@Composable
fun CharacterItem(character: Character) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                        .height(IntrinsicSize.Max)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            AsyncImage(
                model = character.thumbnail.pathSecure + "." + character.thumbnail.extension,
                contentDescription = character.name,
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp)
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

@Preview
@Composable
fun CharacterItemPreview() {
    CharacterItem(
        character = Character(
            id = 1,
            name = "Spider-Man",
            description = "Does whatever a spider can",
            thumbnail = CharacterThumbnail("http://i.annihil.us/u/prod/marvel/i/mg/3/80/520288b9cb581", "jpg"),
            comics = CharacterComics(listOf(CharacterComicsItems("Amazing Fantasy 15")))
        )
    )
}