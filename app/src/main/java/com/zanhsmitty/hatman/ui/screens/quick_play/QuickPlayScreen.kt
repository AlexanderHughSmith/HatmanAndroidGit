package com.zanhsmitty.hatman.ui.screens.quick_play
/*
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.zanhsmitty.hatman.ui.screens.components.DisplayDice
import com.zanhsmitty.hatman.ui.theme.SMALL_PADDING


@Composable
fun QuickPlayScreen() {
    var number1 by remember { mutableStateOf(1) }
    var number2 by remember { mutableStateOf(2) }
    var displayText by remember { mutableStateOf("Roll the dice!") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            modifier = Modifier.padding(SMALL_PADDING),
            text = displayText,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = MaterialTheme.colorScheme.onBackground
        )
        DisplayDice(
            Modifier,
            number1,
            number2,
            0f,
            true
        )
        Button(
            onClick = {
                throw RuntimeException("Test Crash") // Force a crash
                number1 = (1..6).random()
                number2 = (1..6).random()
                displayText = handleDisplayText(number1, number2)
            }
        ) {
            Text(text = "Roll Dice")
        }
    }
}

fun handleDisplayText(number1: Int, number2: Int): String {
    //new Hatman
    if(number1 + number2 == 3){
        return "You are now the Hatman!"
    }
    var returnString = ""
    //Double, ahead, or behind
    if(number1 == number2) {
        returnString += "Pick someone to drink for $number1 seconds!\n"
    }
    else if(number1 + number2 == 7){
        returnString += "7 ahead, person ahead takes a drink!\n"
    }
    else if(number1 + number2 == 9){
        returnString += "9 behind, person behind takes a drink!\n"
    }

    //3 check
    if(number1 == 3 && number2 == 3){
        returnString += "3 Hatman drinks twice!\n"
    }
    else if(number1 == 3 || number2 == 3){
        returnString += "3 Hatman drinks!\n"
    }

    //10 check
    if(number1+number2 == 10){
        returnString += "10 community!\n"
    }

    //Empty check
    if(returnString == ""){
        return "Next player rolls!"
    }
    return returnString.dropLast(2)
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    QuickPlayScreen()
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainScreenPreviewDark() {
    QuickPlayScreen()
}*/
