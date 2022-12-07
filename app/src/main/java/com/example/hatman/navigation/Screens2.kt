package com.example.hatman.navigation

import androidx.navigation.NavHostController

class Screens2(navController: NavHostController) {
    val splash: () -> Unit = {
        navController.navigate(Screens.Splash.route) {
            popUpTo(Screens.Splash.route) { inclusive = true }
        }
    }

    val options: () -> Unit = {
        navController.navigate(Screens.Options.route) {
            popUpTo(Screens.Splash.route) { inclusive = true }
        }
    }
}
