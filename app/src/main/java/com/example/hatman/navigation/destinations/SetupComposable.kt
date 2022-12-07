package com.example.hatman.navigation.destinations

import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.hatman.navigation.Screens
import com.example.hatman.ui.SharedViewModel
import com.example.hatman.ui.screens.setup.SetupScreen
import com.example.hatman.util.Constants.MAIN_SCREEN
import com.example.hatman.util.Constants.NORMAL_ANIMATION_SPEED
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.flow.first

@ExperimentalAnimationApi
fun NavGraphBuilder.setupComposable(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
){
    composable(
        route = Screens.Setup.route,
        enterTransition = {
            if(sharedViewModel.gameAlreadySetUp.value){
                fadeIn(animationSpec = tween(100))
            }
            else if(sharedViewModel.comingFromPlayingScreen.value){
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(durationMillis = NORMAL_ANIMATION_SPEED),
                )
            }
            else{
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = NORMAL_ANIMATION_SPEED),
                )
            }
        },
        popExitTransition = {
            if(sharedViewModel.gameAlreadySetUp.value){
                fadeOut(animationSpec = tween(100))
            }
            else{
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = NORMAL_ANIMATION_SPEED)
                )
            }
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = NORMAL_ANIMATION_SPEED)
            )
        },
    ) {
        SetupScreen(
            navController = navController,
            sharedViewModel = sharedViewModel
        )
    }
}