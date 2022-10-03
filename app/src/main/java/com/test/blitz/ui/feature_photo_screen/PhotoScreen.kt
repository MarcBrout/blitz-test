package com.test.blitz.ui.feature_photo_screen

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.PhotoUrls
import com.test.blitz.ui.common.components.InfiniteAnimation
import com.test.blitz.ui.common.components.PhotoGrid

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun PhotoScreen(
    state: PhotoUiState = PhotoUiState(),
    onNavigateToPhoto: (Photo) -> Unit = {},
    onNavigateBack: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Log.e("PhotoScreen", "PhotoScreen: ${state.photo}")
        Crossfade(targetState = state.photo) {
            when  {
                state.photoLoading -> {
                    InfiniteAnimation(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .aspectRatio(1f),
                    )
                }
                it != null -> {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        model = it.urls[PhotoUrls.Regular],
                        contentDescription = null,
                    )
                }
            }
        }

        PhotoGrid(
            photos = state.userPhotos,
            onPhotoClick = onNavigateToPhoto)
    }
}