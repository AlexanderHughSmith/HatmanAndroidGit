package com.example.hatman.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.hatman.ui.SharedViewModel
import com.example.hatman.ui.screens.setup_play.SetupPlayScreen
import com.example.hatman.util.Constants.SETUP_PLAY_SCREEN
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.setupPlayComposable(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
){
    composable(
        route = SETUP_PLAY_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(durationMillis = 300)
            )
        }
    ) {
        SetupPlayScreen(
            navController = navController,
            sharedViewModel = sharedViewModel
        )
    }
}