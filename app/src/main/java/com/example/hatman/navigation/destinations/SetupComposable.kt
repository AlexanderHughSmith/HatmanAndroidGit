package com.example.hatman.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.hatman.ui.SharedViewModel
import com.example.hatman.ui.screens.setup.SetupScreen
import com.example.hatman.util.Constants.MAIN_SCREEN
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.optionsComposable(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
){
    composable(
        route = MAIN_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(durationMillis = 300)
            )
        }
    ) {
        SetupScreen(
            navController = navController,
            sharedViewModel = sharedViewModel
        )
    }
}