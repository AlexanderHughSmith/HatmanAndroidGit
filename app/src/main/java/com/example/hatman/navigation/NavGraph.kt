package com.example.hatman.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hatman.ui.SharedViewModel
import com.example.hatman.ui.screens.TextScreen
import com.example.hatman.ui.screens.setup.SetupScreen
import com.example.hatman.ui.screens.quick_play.QuickPlayScreen
import com.example.hatman.ui.screens.options.OptionsScreen
import com.example.hatman.ui.screens.setup_play.SetupPlayScreen
import com.example.hatman.ui.screens.splash.SplashScreen

@ExperimentalAnimationApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(
                navigateToListScreen = {
                    navController.navigate(Screens.Options.route) {
                        popUpTo(Screens.Splash.route) {
                            inclusive = true
                        }
                    }
               },
                sharedViewModel = sharedViewModel
                )
        }
        composable(route = Screens.QuickPlay.route) {
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
    }
}