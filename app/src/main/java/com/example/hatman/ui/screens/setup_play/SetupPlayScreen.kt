package com.example.hatman.ui.screens.setup_play

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hatman.navigation.Screens
import com.example.hatman.ui.SharedViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupPlayScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    var navigate: Boolean by remember { mutableStateOf(false) }
    if(navigate){
        LaunchedEffect(Unit) {
            coroutineScope.launch {
                sharedViewModel.comingFromPlayingScreen.value = true
                sharedViewModel.deleteAllPlayers()
                navController.popBackStack()
                navController.navigate(Screens.Setup.route)
            }
        }
    }
    Scaffold(
        topBar = {
            SetupPlayAppBar(
                navController = navController,
                onNewGameClicked = {
                    navigate = true
                }
            )
        },
        content = {
            SetupPlayScreenContent(
                modifier = Modifier.padding(it),
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
    )
}