package com.zanhsmitty.hatman.ui.screens.setup_play

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.zanhsmitty.hatman.navigation.Screens
import com.zanhsmitty.hatman.ui.SharedViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
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
                sharedViewModel.handleNewGameDataStore()
                navController.navigate(Screens.Setup.route){
                    navController.popBackStack()
                }
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
                sharedViewModel = sharedViewModel
            )
        }
    )
}