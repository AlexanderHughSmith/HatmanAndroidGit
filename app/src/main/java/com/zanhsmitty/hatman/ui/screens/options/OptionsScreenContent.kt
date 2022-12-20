package com.zanhsmitty.hatman.ui.screens.options

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zanhsmitty.hatman.R
import com.zanhsmitty.hatman.data.model.Player
import com.zanhsmitty.hatman.navigation.Screens
import com.zanhsmitty.hatman.ui.SharedViewModel
import com.zanhsmitty.hatman.ui.theme.LARGER_PADDING
import com.zanhsmitty.hatman.ui.theme.MEDIUM_PADDING
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun OptionsScreenContent(
    modifier: Modifier,
    navController: NavHostController,
    playerList: StateFlow<List<Player>>,
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
        Image(
            painter = painterResource(id = R.drawable.single_dice),
            contentDescription = "Giant die"
        )
        Row(
            modifier = Modifier.padding(horizontal = LARGER_PADDING)
        ){
            Button(
                modifier = Modifier.weight(5f),
                onClick = {
                    coroutineScope.launch {
                        navigate = 3
                    }
                }
            ){
                Text(
                    text = "Rules",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier.weight(5f),
                onClick = {
                    coroutineScope.launch {
                        navigate = if(playerList.first().isNotEmpty()) 4 else 1
                    }
                },
            ){
                Text(
                    text = "Play",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
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
    OptionsScreenContent(
        modifier = Modifier,
        navController = rememberNavController(),
        playerList = MutableStateFlow<List<Player>>(listOf())
    )
}