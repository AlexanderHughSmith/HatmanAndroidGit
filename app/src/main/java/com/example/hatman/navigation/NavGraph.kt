package com.example.hatman.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hatman.ui.SharedViewModel
import com.example.hatman.ui.screens.TextScreen
import com.example.hatman.ui.screens.about.AboutScreen
import com.example.hatman.ui.screens.how_to_play.HowToPlayScreen
import com.example.hatman.ui.screens.setup.SetupScreen
import com.example.hatman.ui.screens.quick_play.QuickPlayScreen
import com.example.hatman.ui.screens.options.OptionsScreen
import com.example.hatman.ui.screens.setup_play.SetupPlayScreen
import com.example.hatman.ui.screens.splash.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.example.hatman.navigation.Screens2
import com.example.hatman.navigation.destinations.*
import com.example.hatman.util.Constants.SPLASH_SCREEN

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
            { navController.navigate(Screens.Options.route) },
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