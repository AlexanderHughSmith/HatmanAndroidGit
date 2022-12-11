package com.zanhsmitty.hatman.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.zanhsmitty.hatman.ui.SharedViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.zanhsmitty.hatman.navigation.destinations.*

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    /*val screen = remember(navController) {
        Screens2(navController = navController)
    }*/

    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        splashComposable (
            sharedViewModel = sharedViewModel,
            navigateToMainScreen =
            {
                navController.navigate(Screens.Options.route) {
                    popUpTo(0){
                        inclusive = true
                    }
                }
            }
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
    }
}