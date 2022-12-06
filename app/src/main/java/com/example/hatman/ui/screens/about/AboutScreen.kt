package com.example.hatman.ui.screens.about

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.hatman.ui.screens.options.SetupScreenAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            AboutScreenAppBar(
                navController = navController
            )
        },
        content = {
            AboutContent(
                modifier = Modifier.padding(it)
            )
        }
    )
}