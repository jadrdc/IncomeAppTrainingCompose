package com.koombea.presenter.ui.character.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.koombea.domain.model.CharacterModel
import com.koombea.presenter.ui.character.CharacterListViewModel
import com.koombea.presenter.ui.widget.LoadingSpinner


@Composable
fun CharacterListScreen(viewModel: CharacterListViewModel,
                        onClick:(CharacterModel)->Unit) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    when (state.loading) {
        true -> {
            LoadingSpinner()
        }

        false -> {
            LazyColumn {
                items(items = state.list ?: emptyList(),
                    itemContent = { item ->
                        ListItemView(item,onClick = onClick)
                    })
            }
        }

        else -> {}
    }
}


@Composable
fun ListItemView(item: CharacterModel,onClick:(CharacterModel)->Unit) {
    Row(modifier = Modifier.padding(16.dp).clickable {
        onClick.invoke(item)
    }) {
        // Load the image from the remote URL using Glide
        Box(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .clip(RoundedCornerShape(8))
            )
        }
        Box(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth()
        ) {
            Text(
                text = item.name,
                modifier = Modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
