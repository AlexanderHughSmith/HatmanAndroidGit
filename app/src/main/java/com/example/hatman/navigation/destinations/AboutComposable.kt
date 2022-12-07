package com.example.hatman.navigation.destinations

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.hatman.navigation.Screens
import com.example.hatman.ui.SharedViewModel
import com.example.hatman.ui.screens.about.AboutScreen
import com.example.hatman.ui.screens.how_to_play.HowToPlayScreen
import com.example.hatman.ui.screens.setup.SetupScreen
import com.example.hatman.util.Constants.MAIN_SCREEN
import com.example.hatman.util.Constants.NORMAL_ANIMATION_SPEED
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.aboutComposable(
    navController: NavHostController,
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
            navController = navController
        )
    }
}