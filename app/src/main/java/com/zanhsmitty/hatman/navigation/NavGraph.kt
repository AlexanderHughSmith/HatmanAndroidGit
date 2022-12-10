package com.zanhsmitty.hatman.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.zanhsmitty.hatman.ui.SharedViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.zanhsmitty.hatman.navigation.destinations.*
import com.zanhsmitty.hatman.util.Constants.SPLASH_SCREEN

@ExperimentalAnimationApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val screen = remember(navController) {
        Screens2(navController = navController)
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable (
            {
                navController.navigate(Screens.Options.route) {
                    popUpTo(0){
                        inclusive = true
                    }
                }
            },
            sharedViewModel
        )
        optionsComposable(
            navController = navController,
            sharedViewModel = sharedViewModel
        )
        howToPlayComposable(
            navController = navController
        )
        setupComposable(
            navController = navController,
            sharedViewModel = sharedViewModel
        )
        setupPlayComposable(
            navController = navController,
            sharedViewModel = sharedViewModel
        )
        aboutComposable(
            navController = navController,
            sharedViewModel = sharedViewModel
        )

        /*composable(route = Screens.Splash.route) {
            SplashScreen(
                navigateToListScreen = {
                    navController.navigate(Screens.Options.route) {
                        popUpTo(Screens.Splash.route) {
                            inclusive = true
                        }
                    }
               },
        }*/
        /*composable(route = Screens.QuickPlay.route) {
            QuickPlayScreen()
        }
        composable(route = Screens.Setup.route) {
            SetupScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
        composable(route = Screens.Options.route) {
            OptionsScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
        composable(route = Screens.SetupPlay.route) {
            SetupPlayScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
        composable(route = Screens.Text.route) {
            TextScreen(
                sharedViewModel = sharedViewModel
            )
        }
        composable(route = Screens.HowToPlay.route) {
            HowToPlayScreen(
                navController = navController
            )
        }
        composable(route = Screens.About.route) {
            AboutScreen(
                navController = navController
            )
        }*/
    }
}