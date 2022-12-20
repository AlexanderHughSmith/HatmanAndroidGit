package com.zanhsmitty.hatman.ui.screens.setup_play

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zanhsmitty.hatman.ui.SharedViewModel
import com.zanhsmitty.hatman.ui.components.DisplayDice
import com.zanhsmitty.hatman.ui.components.LeaderBoard
import com.zanhsmitty.hatman.ui.theme.LARGE_PADDING
import kotlinx.coroutines.launch

@Composable
fun SetupPlayScreenContent(
    modifier: Modifier,
    sharedViewModel: SharedViewModel
) {
    val die1 by remember { mutableStateOf(sharedViewModel.die1) }
    val die2 by remember { mutableStateOf(sharedViewModel.die2) }
    val dieShown by remember { mutableStateOf(sharedViewModel.isDieShown) }
    var isDieEnabled by remember { mutableStateOf(true) }
    val displayText by remember {
        mutableStateOf(
            sharedViewModel.displayText
        )
    }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier.fillMaxSize()
    ){
        LeaderBoard(
            playerList = sharedViewModel.players.collectAsState().value,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.weight(3f))
        Text(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = displayText.value
        )
        DisplayDice(
            modifier = Modifier.weight(2f),
            number1 = die1.value,
            number2 = die2.value,
            rotateAngle = sharedViewModel.rotateAngle.value,
            isDieShown = dieShown.value
        )
        Button(
            modifier = Modifier
                .height(75.dp)
                .fillMaxWidth(.5F)
                .padding(bottom = LARGE_PADDING)
                .align(Alignment.CenterHorizontally),
            enabled = isDieEnabled && dieShown.value,
            onClick = {
                isDieEnabled = false
                coroutineScope.launch {
                    sharedViewModel.handleRoll()
                    sharedViewModel.updatePlayers()
                    isDieEnabled = true
                }
            },
        ){
            Text("Roll Dice")
        }
    }
}
