package com.example.hatman.ui.screens.options

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.hatman.ui.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    Log.d("TextScreen", navController.backQueue.toString())
    repeat(navController.backQueue.size) {
        Log.d("TextScreen", navController.backQueue[it].destination.toString())
    }
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