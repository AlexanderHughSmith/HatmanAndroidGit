package com.zanhsmitty.hatman.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.zanhsmitty.hatman.ui.SharedViewModel

@Composable
fun TextScreen(
    sharedViewModel: SharedViewModel
) {
    Log.d("TextScreen", "TextScreenComposable")
    val die1 = sharedViewModel.die1.value
    val die2 = sharedViewModel.die2.value
    val rollDice = sharedViewModel.rollDice2
    var clicked by remember {
        mutableStateOf(false)
    }
    Column{
        Button(
            onClick = {
                if(!clicked){
                    clicked = true
                    rollDice()
                    clicked = false
                }
            }
        ) {
            Text(
                text = "roll",
                fontSize = 20.sp
            )
            Log.d("TextScreen", "ButtonClicked")
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ){
            Log.d("TextScreen", "Row composed")
            Text(
                text = "$die1"
            )
            Text(
                text = "$die2"
            )
        }

    }

}