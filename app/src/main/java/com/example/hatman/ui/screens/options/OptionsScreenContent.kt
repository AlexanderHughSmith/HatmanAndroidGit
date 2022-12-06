package com.example.hatman.ui.screens.options

import android.util.Log
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
import com.example.hatman.navigation.Screens
import com.example.hatman.ui.SharedViewModel
import com.example.hatman.ui.theme.LARGER_PADDING
import com.example.hatman.ui.theme.LARGEST_PADDING
import com.example.hatman.ui.theme.LARGE_PADDING
import com.example.hatman.ui.theme.MEDIUM_PADDING

@Composable
fun OptionsScreenContent(
    modifier: Modifier,
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
) {
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
            }
        }
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        OptionButtonDescription(
            modifier = Modifier,
            message = "Setup Game",
            description = "Enter the names of everyone playing. We will let you know who should take a drink by name and keep track of everyone's score.",
            onButtonClick = {
                navigate = 1
            }
        )
        OptionButtonDescription(
            modifier = Modifier,
            message = "Quick Play",
            description = "Roll 2 dice and we will tell you the role of the player that should take a drink.",
            onButtonClick = {
                navigate = 2
            }
        )
        OptionButtonDescription(
            modifier = Modifier,
            message = "How To Play",
            description = "Look over the rules and learn how to play the game.",
            onButtonClick = {
                navigate = 3
            }
        )
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