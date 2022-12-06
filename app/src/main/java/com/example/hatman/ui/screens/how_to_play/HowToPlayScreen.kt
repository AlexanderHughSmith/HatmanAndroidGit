package com.example.hatman.ui.screens.how_to_play

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.hatman.ui.SharedViewModel
import com.example.hatman.ui.screens.options.SetupScreenAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HowToPlayScreen(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            SetupScreenAppBar(
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