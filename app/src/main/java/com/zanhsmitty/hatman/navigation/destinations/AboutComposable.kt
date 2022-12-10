package com.zanhsmitty.hatman.navigation.destinations

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.zanhsmitty.hatman.navigation.Screens
import com.zanhsmitty.hatman.ui.SharedViewModel
import com.zanhsmitty.hatman.ui.screens.about.AboutScreen
import com.zanhsmitty.hatman.util.Constants.NORMAL_ANIMATION_SPEED
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.aboutComposable(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
){
    composable(
        route = Screens.About.route,
        enterTransition = {
          slideInHorizontally(
              initialOffsetX = { fullWidth -> fullWidth },
              animationSpec = tween(durationMillis = NORMAL_ANIMATION_SPEED),
          )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(durationMillis = NORMAL_ANIMATION_SPEED)
            )
        },
    ) {
        AboutScreen(
            navController = navController,
            sharedViewModel = sharedViewModel
        )
    }
}