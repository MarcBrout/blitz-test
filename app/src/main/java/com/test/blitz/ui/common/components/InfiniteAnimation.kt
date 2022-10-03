package com.test.blitz.ui.common.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.test.blitz.ui.theme.Animation

@Preview
@Composable
fun InfiniteAnimation(
    modifier: Modifier = Modifier,
    compositionSpec: LottieCompositionSpec = Animation.loading,
    speed: Float = 1f,
) {
    val composition by rememberLottieComposition(spec = compositionSpec)

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        speed = speed,
        iterations = LottieConstants.IterateForever,
    )
}