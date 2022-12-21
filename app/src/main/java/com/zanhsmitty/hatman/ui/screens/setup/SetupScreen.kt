package com.zanhsmitty.hatman.ui.screens.setup

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zanhsmitty.hatman.ui.SharedViewModel

@ExperimentalMaterial3Api
@Composable
fun SetupScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            SetupAppBar(navController = navController)
        },
        content = {
            SetupScreenContent(
                modifier = Modifier.padding(it),
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
    )
}

@ExperimentalMaterial3Api
@Composable
fun PlayerRow(
    playerNumber: Int,
    playerName:String,
    onTextChanged: (String) -> Unit,
    isDelete: Boolean = false,
    onDelete: () -> Unit = {}
){
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            modifier = Modifier
                .semantics { contentDescription = "Player-$playerNumber-textField" }
                .fillMaxWidth(.7f)
                .padding(8.dp),
            value = playerName,
            onValueChange = { onTextChanged(it) },
            label = { Text("Player $playerNumber") },
            singleLine = true
        )
        Button(
            modifier = Modifier
                .size(70.dp)
                .padding(16.dp)
                .align(Alignment.CenterEnd),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.error
            ),
            onClick = {
                onDelete()
            },
            enabled = isDelete
        ) {
            Text(
                text = "-",
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun PlayerRowPreview() {
    val players = remember{
        mutableStateListOf("", "", "", "")
    }
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        Column(
            modifier = Modifier
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
                        players.add("")
                    },
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
                        onTextChanged = { players[players.indexOf(player)] = it },
                        isDelete = players.size > 3,
                        onDelete = {
                            players.remove(player)
                            Log.d("SetupScreen", players.toString())
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
}
