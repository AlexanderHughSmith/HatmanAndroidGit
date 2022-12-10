package com.zanhsmitty.hatman.ui.screens.about

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.zanhsmitty.hatman.ui.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            AboutScreenAppBar(
                navController = navController
            )
        },
        content = {
            AboutContent(
                modifier = Modifier.padding(it),
                sharedViewModel = sharedViewModel
            )
        }
    )
}