package com.koombea.presenter.ui.character.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.koombea.domain.model.CharacterModel

@Composable
fun CharacterDetailScreen(item : CharacterModel?){
    Column(modifier = Modifier.padding(16.dp)) {
        // Load the image from the remote URL using Glide
        Box(
            contentAlignment = Alignment.Center,
                    modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item?.image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(300.dp)
                    .clip(RoundedCornerShape(8))
            )
        }
       Box(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth().padding(top = 20.dp)
        ) {
            Text(
                text = item?.name ?: "N/A",
                modifier = Modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}