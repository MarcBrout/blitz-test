package com.test.blitz.ui.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import com.test.blitz.R
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.PhotoUrls

@Composable
fun LazyPhotoGrid(
    modifier : Modifier = Modifier,
    photos: List<Photo>,
    onPhotoClick: (Photo) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier,
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_small)),
    ) {
        items(
            items = photos,
            key = { it.id },
            itemContent = { photo ->
                PhotoThumb(
                    photo = photo,
                    navigateToUserPhotos = onPhotoClick,
                )
            }
        )

    }
}




@Composable
fun PhotoThumb(
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
