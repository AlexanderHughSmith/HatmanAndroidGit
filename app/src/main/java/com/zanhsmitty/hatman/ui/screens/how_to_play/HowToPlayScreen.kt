package com.zanhsmitty.hatman.ui.screens.how_to_play

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@ExperimentalMaterial3Api
@Composable
fun HowToPlayScreen(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            HowToPlayAppBar(
                navController = navController
            )
        },
        content = {
            HowToPlayContent(
                modifier = Modifier.padding(it)
            )
        }
    )
}