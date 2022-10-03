package com.test.blitz.ui.feature_photo_screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.PhotoUrls
import com.test.blitz.ui.common.components.InfiniteAnimation
import com.test.blitz.ui.common.components.LazyPhotoGrid
import com.test.blitz.R
import com.test.blitz.ui.theme.LightGrey
import com.test.blitz.ui.theme.TextGrey
import kotlin.math.roundToInt

@Composable
fun PhotoScreen(
    state: PhotoUiState = PhotoUiState(),
    onShowFullScreen: (Photo) -> Unit = {},
    onNavigateToPhoto: (Photo) -> Unit = {},
    onNavigateBack: () -> Unit = {},
) {

    val photoHeight = LocalConfiguration.current.screenWidthDp.dp
    val photoHeightPx = with(LocalDensity.current) { photoHeight.roundToPx().toFloat() }

    // our offset to collapse toolbar
    val photoOffsetPx = remember { mutableStateOf(0f) }
    // now, let's create connection to the nested scroll system and listen to the scroll
    // happening inside child LazyColumn
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // try to consume before LazyColumn to collapse toolbar if needed, hence pre-scroll
                val delta = available.y
                val newOffset = photoOffsetPx.value + delta
                photoOffsetPx.value = newOffset.coerceIn(-photoHeightPx, 0f)
                // here's the catch: let's pretend we consumed 0 in any case, since we want
                // LazyColumn to scroll anyway for good UX
                // We're basically watching scroll without taking it
                return Offset.Zero
            }
        }
    }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        Crossfade(
            modifier = Modifier
                .scale(scaleX = 0f, scaleY = (1f - photoOffsetPx.value / photoHeightPx).coerceIn(0f, 1f)),
            targetState = state.photo,
        ) {
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
                    Column {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clickable {
                                    onShowFullScreen(it)
                                },
                            model = it.urls[PhotoUrls.Regular],
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = LightGrey)
                                .padding(horizontal = dimensionResource(id = R.dimen.margin)),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        vertical = dimensionResource(id = R.dimen.margin_small),
                                    ),
                                text = it.user.name
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Icon(
                                Icons.Rounded.FavoriteBorder,
                                null,
                                tint = TextGrey
                            )

                            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.margin_small)))

                            Icon(
                                painterResource(id = R.drawable.round_bookmark_border_24),
                                null,
                                tint = TextGrey
                            )
                        }

                        Text(
                            modifier = Modifier
                                .padding(
                                    horizontal = dimensionResource(id = R.dimen.margin),
                                    vertical = dimensionResource(id = R.dimen.margin_small),
                                ),
                            text = it.description ?: ""
                        )

                        StatisticRow(
                            value = it.createdAt,
                            icon = painterResource(id = R.drawable.round_calendar_today_20),
                        )

                        StatisticRow(
                            value = state.statistics?.views?.toString() ?: "",
                            icon = painterResource(id = R.drawable.round_visibility_20),
                        )

                        StatisticRow(
                            value = state.statistics?.likes?.toString() ?: "",
                            icon = rememberVectorPainter(image = Icons.Rounded.Favorite),
                        )

                        StatisticRow(
                            value = state.statistics?.downloads?.toString() ?: "",
                            icon = painterResource(id = R.drawable.round_file_download_20),
                        )
                    }
                }
            }
        }

        Text(
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.margin),
                    vertical = dimensionResource(id = R.dimen.margin_small),
                ),
            text = "Related photos"
        )

        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        LazyPhotoGrid(
            modifier = Modifier
                .fillMaxSize()
                .height(screenHeight)
                .padding(
                    top = dimensionResource(id = R.dimen.margin),
                ),
            photos = state.userPhotos,
            onPhotoClick = onNavigateToPhoto)
    }
}

@Composable
private fun StatisticRow(
    value: String?,
    icon: Painter,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.margin),
                vertical = dimensionResource(id = R.dimen.margin_small),
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            icon,
            null,
            modifier = Modifier.size(24.dp),
            tint = Color.Black,
        )

        Text(
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.margin_small),
                ),
            text = value ?: ""
        )
    }
}