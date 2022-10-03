package com.test.blitz.ui.feature_home_screen

import com.test.blitz.core.mvi.NavTarget

sealed class HomeNavTarget : NavTarget {
    class PhotoDetails(val photoId: String) : HomeNavTarget()
}