package com.hermes.marvelapp.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.hermes.marvelapp.R
import com.hermes.marvelapp.data.local.CharacterEntity
import com.hermes.marvelapp.utils.testCharacter

@Composable
fun CharacterDetail(singleCharacter: CharacterEntity, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .padding(top = 30.dp, bottom = 42.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailTop(
            navController = navController,
            modifier = Modifier
                .fillMaxHeight(0.2f)
                .align(Alignment.Start)
        )
        val url = singleCharacter.path + "." + singleCharacter.extension
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(200.dp)
                .padding(top = 32.dp)
                .align(Alignment.CenterHorizontally))

            Text(
                text = singleCharacter.name,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = singleCharacter.comics,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Left,
                fontSize = 11.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(8.dp),
                lineHeight = 20.sp
            )
        
        Text(
            text = singleCharacter.description,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}


@Preview
@Composable
fun previewCharacterDetail() {
    CharacterDetail(singleCharacter = testCharacter(), navController = rememberNavController())
}