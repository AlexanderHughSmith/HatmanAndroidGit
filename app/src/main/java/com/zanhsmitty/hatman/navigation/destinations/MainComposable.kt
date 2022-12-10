package com.zanhsmitty.hatman.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.zanhsmitty.hatman.ui.screens.quick_play.QuickPlayScreen
import com.zanhsmitty.hatman.util.Constants.MAIN_SCREEN
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.mainComposable(){
    composable(
        route = MAIN_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(durationMillis = 300)
            )
        }
    ) {
        QuickPlayScreen()
    }
}