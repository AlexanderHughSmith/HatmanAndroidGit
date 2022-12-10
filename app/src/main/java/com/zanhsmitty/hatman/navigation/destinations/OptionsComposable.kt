package com.zanhsmitty.hatman.navigation.destinations

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.zanhsmitty.hatman.navigation.Screens
import com.zanhsmitty.hatman.ui.SharedViewModel
import com.zanhsmitty.hatman.ui.screens.options.OptionsScreen
import com.zanhsmitty.hatman.util.Constants.NORMAL_ANIMATION_SPEED
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