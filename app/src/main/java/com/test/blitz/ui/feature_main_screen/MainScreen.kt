package com.test.blitz.ui.feature_main_screen

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.test.blitz.ui.feature_home_screen.HomeScreen
import com.test.blitz.R
import com.test.blitz.ui.SearchScreen
import com.test.blitz.ui.feature_home_screen.HomeViewModel
import com.test.blitz.ui.feature_photo_screen.PhotoScreen
import com.test.blitz.ui.feature_photo_screen.PhotoViewModel

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : Screen("home", R.string.home, Icons.Rounded.Home)
    object Search : Screen("search", R.string.search, Icons.Rounded.Search)
    object Add : Screen("Add", R.string.add, Icons.Rounded.Add)
    object Favorites : Screen("favorites", R.string.favorites, Icons.Rounded.Favorite)
    object Profile : Screen("profile", R.string.profile, Icons.Rounded.Person)

    companion object {
        fun values() = listOf(Home, Search, Add, Favorites, Profile)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable()
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Screen.values().forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                        selected = currentDestination?.route == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(screen.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(navController = navController, startDestination = Screen.Home.route) {
                composable(Screen.Home.route) {
                    val homeViewModel = hiltViewModel<HomeViewModel>()
                    val state = homeViewModel.state.collectAsState()

                    HomeScreen(
                        state = state.value,
                        navigateToUserPhotos = { photo ->
                            navController.navigate("photo/${photo.id}")
                        }
                    )
                }

                composable("photo/{photoId}") {
                    val photoId = it.arguments?.getString("photoId")
                    if (photoId != null) {
                        val photoViewModel = hiltViewModel<PhotoViewModel>()
                        val state = photoViewModel.state.collectAsState()

                        PhotoScreen(
                            state = state.value,
                            onNavigateToPhoto = { photo ->
                                navController.navigate("photo/${photo.id}") {
                                    popUpTo(Screen.Home.route)
                                }
                            },
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }

                composable(Screen.Search.route) { SearchScreen() }
                composable(Screen.Add.route) { SearchScreen() }
                composable(Screen.Favorites.route) { SearchScreen() }
                composable(Screen.Profile.route) { SearchScreen() }
            }
        }
    }
}