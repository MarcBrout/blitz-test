package com.test.blitz.ui.feature_home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.blitz.R
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.PhotoUrls
import com.test.blitz.ui.common.components.InfiniteAnimation
import com.test.blitz.ui.theme.Typography
import java.time.format.TextStyle

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun BoxScope.HomeScreen(
    state: HomeUiState = HomeUiState(),
    navigateToUserPhotos: (Photo) -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.margin),
                    bottom = dimensionResource(id = R.dimen.margin),
                    start = dimensionResource(id = R.dimen.margin_large),
                )
                .fillMaxWidth(),
            text = "Unsplash Explorer",
            style = Typography.h4,
            textAlign = TextAlign.Start,
        )

        when {
            state.isLoading -> {
                InfiniteAnimation(
                    modifier = Modifier
                        .size(100.dp)
                )
            }
            state.error != null -> {
                Text(
                    text = state.error.localizedMessage ?: state.error.message ?: state.error.toString(),
                )
            }
            else -> {
                HomeScreenContent(
                    state = state,
                    navigateToUserPhotos = navigateToUserPhotos
                )
            }
        }
    }

}

@Composable
private fun HomeScreenContent(
    state: HomeUiState,
    navigateToUserPhotos: (Photo) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_small)),
    ) {
        items(
            items = state.photos,
            key = { it.id },
            itemContent = { photo ->
                PhotoThumb(
                    photo = photo,
                    navigateToUserPhotos = navigateToUserPhotos,
                )
            }
        )

    }
}

@Composable
private fun PhotoThumb(
    photo: Photo,
    navigateToUserPhotos: (Photo) -> Unit,
) {
    AsyncImage(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable {
                navigateToUserPhotos(photo)
            },
        model = photo.urls[PhotoUrls.Thumb],
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}
