package com.zanhsmitty.hatman.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.zanhsmitty.hatman.ui.SharedViewModel
import com.zanhsmitty.hatman.ui.screens.splash.SplashScreen
import com.zanhsmitty.hatman.util.Constants.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.splashComposable(
    navigateToMainScreen: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = SPLASH_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(durationMillis = 300)
            )
        }
    ) {
        SplashScreen(navigateToListScreen = navigateToMainScreen, sharedViewModel = sharedViewModel)
    }
}