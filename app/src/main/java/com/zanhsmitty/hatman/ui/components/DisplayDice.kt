package com.zanhsmitty.hatman.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.zanhsmitty.hatman.ui.theme.LOGO_HEIGHT
import com.zanhsmitty.hatman.R

//TODO: Ability to customize dice

const val dieOne: Int = R.drawable.one
const val dieTwo: Int = R.drawable.two
const val dieThree: Int = R.drawable.three
const val dieFour: Int = R.drawable.four
const val dieFive: Int = R.drawable.five
const val dieSix: Int = R.drawable.six

@Composable
fun DisplayDice(
    modifier:Modifier,
    number1: Int,
    number2: Int,
    rotateAngle: Float,
    isDieShown: Boolean
) {

    val dice1: Int = setUpDie(number1)
    val dice2: Int = setUpDie(number2)
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