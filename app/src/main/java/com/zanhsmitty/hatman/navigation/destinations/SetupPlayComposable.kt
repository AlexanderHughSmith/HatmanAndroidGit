package com.zanhsmitty.hatman.navigation.destinations

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.zanhsmitty.hatman.navigation.Screens
import com.zanhsmitty.hatman.ui.SharedViewModel
import com.zanhsmitty.hatman.ui.screens.setup_play.SetupPlayScreen
import com.zanhsmitty.hatman.util.Constants.NORMAL_ANIMATION_SPEED
import com.google.accompanist.navigation.animation.composable

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
fun NavGraphBuilder.setupPlayComposable(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
){
    composable(
        route = Screens.SetupPlay.route,
        enterTransition = {
              slideInHorizontally (
                  initialOffsetX = { fullWidth -> fullWidth },
                  animationSpec = tween(durationMillis = NORMAL_ANIMATION_SPEED)
              )
        },
        popExitTransition = {
            slideOutHorizontally (
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(durationMillis = NORMAL_ANIMATION_SPEED)
            )
        },
        popEnterTransition = {
            slideInHorizontally (
                initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = NORMAL_ANIMATION_SPEED)
            )
        },
        exitTransition = {
            if(sharedViewModel.comingFromPlayingScreen.value){
                slideOutHorizontally (
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = NORMAL_ANIMATION_SPEED)
                )
            } else {
                slideOutHorizontally (
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(durationMillis = NORMAL_ANIMATION_SPEED)
                )
            }
        }
    ) {
        SetupPlayScreen(
            navController = navController,
            sharedViewModel = sharedViewModel
        )
    }
}