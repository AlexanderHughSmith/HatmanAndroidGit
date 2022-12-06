package com.example.hatman.ui.screens.options

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hatman.navigation.Screens
import com.example.hatman.ui.SharedViewModel

@Composable
fun OptionsScreenContent(
    modifier : Modifier,
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
) {
    var navigate: Int by remember { mutableStateOf(0) }
    if(navigate != 0){
        LaunchedEffect(Unit) {
            when (navigate) {
                1 -> {
                    navController.navigate(Screens.Setup.route)
                }
                2 -> {
                    navController.navigate(Screens.QuickPlay.route)
                }
                3 -> {
                    navController.navigate(Screens.HowToPlay.route)
                }
            }
        }
    }
    Log.d("TextScreen", navController.backQueue.toString())
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Button(
            onClick = {
                navigate = 1
            }
        ){
            Text("Setup Game")
        }
        Button(
            onClick = {
                navigate = 2
            }
        ){
            Text("Quick Play")
        }
        Button(
            onClick = {
                navigate = 3
            }
        ){
            Text("How To Play")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SetupScreenPreview() {
    OptionsScreenContent(Modifier, navController = rememberNavController(), viewModel())
}