package com.example.hatman.navigation.destinations

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.hatman.navigation.Screens
import com.example.hatman.ui.SharedViewModel
import com.example.hatman.ui.screens.options.OptionsScreen
import com.example.hatman.util.Constants.NORMAL_ANIMATION_SPEED
import com.example.hatman.util.Constants.SETUP_SCREEN
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.optionsComposable(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
) {
    composable(
        route = Screens.Options.route,
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = NORMAL_ANIMATION_SPEED)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = NORMAL_ANIMATION_SPEED)
            )
        }
    /*popEnterTransition = {
        fadeIn(animationSpec = tween(100))
    }*/
    ) {
        OptionsScreen(navController = navController, sharedViewModel = sharedViewModel)
    }
}