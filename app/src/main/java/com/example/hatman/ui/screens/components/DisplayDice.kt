package com.example.hatman.ui.screens.components

import android.media.Image
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.hatman.R
import com.example.hatman.ui.theme.LOGO_HEIGHT
import com.example.hatman.ui.theme.MEDIUM_PADDING

val dieOne: Int = R.drawable.one
val dieTwo: Int = R.drawable.two
val dieThree: Int = R.drawable.three
val dieFour: Int = R.drawable.four
val dieFive: Int = R.drawable.five
val dieSix: Int = R.drawable.six

@Composable
fun DisplayDice(
    modifier:Modifier,
    number1: Int,
    number2: Int,
    rotateAngle: Float,
    isDieShown: Boolean
) {

    var dice1: Int = setUpDie(number1)
    var dice2: Int = setUpDie(number2)
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        if(isDieShown){
            Image(
                modifier = Modifier
                    .size(LOGO_HEIGHT)
                    .rotate(rotateAngle),
                painter = painterResource(id = dice1),
                contentDescription = "Die One"
            )
            Image(
                modifier = Modifier
                    .size(LOGO_HEIGHT)
                    .rotate(-rotateAngle),
                painter = painterResource(id = dice2),
                contentDescription = "Die Two"
            )
        }
    }
}

fun setUpDie(number: Int): Int {
    return when (number) {
        1 -> dieOne
        2 -> dieTwo
        3 -> dieThree
        4 -> dieFour
        5 -> dieFive
        6 -> dieSix
        else -> dieOne
    }
}

@Preview
@Composable
fun DisplayDicePreview() {
    DisplayDice(Modifier,3,4,0f, true)
}