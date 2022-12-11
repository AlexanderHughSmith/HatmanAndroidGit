package com.zanhsmitty.hatman.ui.screens.setup

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zanhsmitty.hatman.navigation.Screens
import com.zanhsmitty.hatman.ui.SharedViewModel
import com.zanhsmitty.hatman.util.Constants.MAX_PLAYERS
import com.zanhsmitty.hatman.util.Constants.MIN_PLAYERS
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun SetupScreenContent(
    modifier: Modifier,
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    sharedViewModel.comingFromPlayingScreen.value = false
    val players = remember{ mutableStateListOf("one", "two", "three", "four") }
    val coroutineScope = rememberCoroutineScope()
    var navigate: Boolean by remember { mutableStateOf(false) }
    if(navigate){
        LaunchedEffect(Unit) {
            navController.navigate(Screens.SetupPlay.route){
                popUpTo(Screens.Options.route){
                    inclusive = false
                }
            }
        }
    }
    navController.backQueue.forEachIndexed { index, backStackEntry ->
        Log.d("SetupScreenContent", "index:"+index+" "+backStackEntry.destination.toString())
    }
    SetupScreenContentView(
        modifier = modifier,
        players = players,
        onAddPlayerClicked = {
            players.add("")
         },
        onRemovePlayerClicked = {
            players.removeAt(it)
        },
        onLetsPlayClicked = {
            coroutineScope.launch {
                sharedViewModel.addPlayers(players)
                navigate = true
            }
        },
    )
}

@ExperimentalMaterial3Api
@Composable
fun SetupScreenContentView(
    modifier: Modifier,
    players: MutableList<String>,
    onAddPlayerClicked: () -> Unit,
    onRemovePlayerClicked: (Int) -> Unit,
    onLetsPlayClicked: () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxSize()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 32.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = {
                    onAddPlayerClicked()
                },
                enabled = players.size < MAX_PLAYERS
            ) {
                Text(
                    text = "Add Player +"
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            itemsIndexed(
                items = players
            ) { index, player ->
                PlayerRow(
                    playerNumber = index + 1,
                    playerName = player,
                    onTextChanged = {
                        players[index] = it
                                    },
                    isDelete = players.size > MIN_PLAYERS,
                    onDelete = {
                        onRemovePlayerClicked(index)
                    }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 32.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    onLetsPlayClicked()
                },
            ) {
                Text(
                    text = "Let's Play!",
                    fontSize = 24.sp
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SetupScreenContentViewPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        val players = remember{
            mutableStateListOf("", "", "", "")
        }
        SetupScreenContentView(
            modifier = Modifier,
            players = players,
            onAddPlayerClicked = {
                players.add("")
            },
            onRemovePlayerClicked = {
                players.removeAt(it)
            },
            onLetsPlayClicked = {},
        )
    }
}