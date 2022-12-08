package com.example.hatman.ui.screens.setup

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hatman.navigation.Screens
import com.example.hatman.ui.SharedViewModel
import com.example.hatman.util.Constants.MAX_PLAYERS
import com.example.hatman.util.Constants.MIN_PLAYERS
import kotlinx.coroutines.launch

@Composable
fun SetupScreenContent(
    modifier: Modifier,
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    //val players = remember{ mutableStateListOf("", "", "", "") }
    sharedViewModel.comingFromPlayingScreen.value = false
    val players = remember{ mutableStateListOf("one", "two", "three", "four") }
    val coroutineScope = rememberCoroutineScope()
    var navigate: Boolean by remember { mutableStateOf(false) }
    if(navigate){
        LaunchedEffect(Unit) {
            //navController.popBackStack(Screens.Options.route, false)
            navController.navigate(Screens.SetupPlay.route){
                popUpTo(Screens.Options.route){
                    inclusive = false
                }
            }
        }
    }
    navController.backQueue.forEachIndexed() { index, backStackEntry ->
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
                playerRow(
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

@Preview(showBackground = true)
@Composable
fun SetupScreenContentViewPreview() {
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
        onLetsPlayClicked = {
            //navController.navigate("setup_play")
        },
    )
}