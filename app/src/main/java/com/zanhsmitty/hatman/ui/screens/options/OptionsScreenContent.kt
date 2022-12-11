package com.zanhsmitty.hatman.ui.screens.options

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zanhsmitty.hatman.navigation.Screens
import com.zanhsmitty.hatman.ui.SharedViewModel
import com.zanhsmitty.hatman.ui.theme.LARGER_PADDING
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun OptionsScreenContent(
    modifier: Modifier,
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    var navigate: Int by remember { mutableStateOf(0) }
    if (navigate != 0) {
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
                4 -> {
                    navController.navigate(Screens.SetupPlay.route)
                }
            }
        }
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                coroutineScope.launch {
                    navigate = if(sharedViewModel.players.first().isNotEmpty()) 4 else 1
                }
            }
        ){
           Text(
               text = "Let's Play!",
               style = MaterialTheme.typography.headlineLarge,
           )
        }
        Button(
            onClick = {
                coroutineScope.launch {
                    navigate = 3
                }
            }
        ){
            Text(
                text = "How to Play",
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    }
}

//composable with button and then description text
@Composable
fun OptionButtonDescription(
    modifier: Modifier,
    message: String,
    onButtonClick: () -> Unit,
    description: String,
) {
    Column(
        modifier = modifier.padding(horizontal = LARGER_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                onButtonClick()
            }
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OptionButtonDescriptionPreview() {
    OptionButtonDescription(
        modifier = Modifier,
        message = "Setup Game",
        description = "Setup a game with your friends",
        onButtonClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun SetupScreenPreview() {
    OptionsScreenContent(Modifier, navController = rememberNavController(), viewModel())
}