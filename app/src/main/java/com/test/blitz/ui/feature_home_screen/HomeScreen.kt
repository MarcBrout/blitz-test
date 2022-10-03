package com.test.blitz.ui.feature_home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.blitz.R
import com.test.blitz.domain.models.Photo
import com.test.blitz.ui.common.components.InfiniteAnimation
import com.test.blitz.ui.common.components.LazyPhotoGrid
import com.test.blitz.ui.theme.Typography

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
                LazyPhotoGrid(
                    photos = state.photos,
                    onPhotoClick = navigateToUserPhotos
                )
            }
        }
    }

}


