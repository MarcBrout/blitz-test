package com.test.blitz.ui.feature_search_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.blitz.R
import com.test.blitz.domain.models.Photo
import com.test.blitz.domain.models.ProfileImageSize
import com.test.blitz.domain.models.User
import com.test.blitz.ui.feature_photo_screen.PhotoItem
import com.test.blitz.ui.theme.Shapes
import com.test.blitz.ui.theme.Typography

@Composable
fun SearchScreen(
    state: SearchUiState,
    onSearchValueChanged: (String) -> Unit = {},
    onPhotoClicked: (Photo) -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.margin),
                            vertical = dimensionResource(id = R.dimen.margin),
                        ),
                    value = state.query,
                    onValueChange = onSearchValueChanged,
                    singleLine = true,
                    enabled = !state.isLoading,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.margin_small))
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                )
        }

        item {
            Text(
                text = "Users",
                style = Typography.h5,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.margin_small))
            )
        }

        item {
            LazyRow(

            ) {
                this@LazyRow.items(
                    items = state.users,
                    itemContent = { user ->
                        UserItem(
                            modifier = Modifier,
                            user = user,
                        )
                    }
                )
            }
        }

        item {
            Text(
                text = "Photos",
                style = Typography.h5,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.margin_small))
            )
        }

        items(
            items = state.photos,
            itemContent = { photo ->
                PhotoItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.margin)),
                    photo = photo,
                    onPhotoClick = onPhotoClicked)
            }
        )
    }
}

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    user: User,
) {
    Column(
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.margin),
                end = dimensionResource(id = R.dimen.margin),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.margin),
                    bottom = dimensionResource(id = R.dimen.margin),
                )
                .size(100.dp)
                .clip(shape = CircleShape)
                .border(
                    1.dp,
                    color = Color.Gray,
                    shape = CircleShape
                ),
            model = user.profileImage[ProfileImageSize.Medium]!!,
            contentDescription = null,
        )

        Text(
            text = user.name,
            style = Typography.subtitle1,
            color = Color.Gray
        )
    }
}