package com.test.blitz.ui.feature_photo_screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.PhotoUrls
import com.test.blitz.ui.common.components.InfiniteAnimation
import com.test.blitz.R
import com.test.blitz.ui.theme.LightGrey
import com.test.blitz.ui.theme.TextGrey
import com.test.blitz.ui.theme.Typography
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PhotoScreen(
    state: PhotoUiState = PhotoUiState(),
    onShowFullScreen: (Photo) -> Unit = {},
    onNavigateToPhoto: (Photo) -> Unit = {},
    onNavigateBack: () -> Unit = {},
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Crossfade(
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
                                value = "Added the " + it.createdAt.toDate(),
                                icon = painterResource(id = R.drawable.round_calendar_today_20),
                            )

                            StatisticRow(
                                value = (state.statistics?.views?.toString() + " views"),
                                icon = painterResource(id = R.drawable.round_visibility_20),
                            )

                            StatisticRow(
                                value = (state.statistics?.likes?.toString() + " likes"),
                                icon = rememberVectorPainter(image = Icons.Rounded.Favorite),
                            )

                            StatisticRow(
                                value = (state.statistics?.downloads?.toString() + " downloads"),
                                icon = painterResource(id = R.drawable.round_file_download_20),
                            )
                        }
                    }
                }
            }
        }

        item {
            Text(
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.margin),
                        vertical = dimensionResource(id = R.dimen.margin_small),
                    ),
                text = "Related photos"
            )
        }

        items(
            items = state.userPhotos,
            itemContent = {
                PhotoItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.margin)),
                    photo = it,
                    onPhotoClick = onShowFullScreen
                )
            }
        )
    }
}

@Composable
fun PhotoItem(
    modifier: Modifier,
    photo: Photo,
    onPhotoClick: (Photo) -> Unit) {

    Column(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable {
                    onPhotoClick(photo)
                },
            model = photo.urls[PhotoUrls.Regular],
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.margin),
                    vertical = dimensionResource(id = R.dimen.margin_small),
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.round_favorite_border_24),
                contentDescription = null,
                tint = Color.DarkGray,
            )

            Text(
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.margin),
                    ),
                text = photo.likes.toString() + " likes",
                style = Typography.body2,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(painter = painterResource(id = R.drawable.round_bookmark_border_24),
                contentDescription = null,
                tint = Color.DarkGray
            )
        }

        Text(
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.margin),
            ),
            style = Typography.body2,
            text = photo.description ?: "Pas de description",
            color = if (photo.description.isNullOrEmpty()) TextGrey else Color.Black,
        )
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

private fun String.toDate(): String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(this)
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date!!)
}