package com.zanhsmitty.hatman.ui.screens.options

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.zanhsmitty.hatman.ui.SharedViewModel

@ExperimentalMaterial3Api
@Composable
fun OptionsScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            SetupScreenAppBar(
                navController = navController
            )
        },
        content = {
            OptionsScreenContent(
                modifier = Modifier.padding(it),
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
    )
}