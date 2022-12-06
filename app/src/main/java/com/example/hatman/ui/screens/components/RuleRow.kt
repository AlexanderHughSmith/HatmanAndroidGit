package com.example.hatman.ui.screens.components

import android.media.Image
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.hatman.R
import com.example.hatman.ui.theme.LOGO_HEIGHT
import com.example.hatman.ui.theme.MEDIUM_PADDING
import com.example.hatman.ui.theme.SMALL_DIE_HEIGHT


@Composable
fun RuleRow(
    modifier:Modifier,
    die1: Int?,
    die2: Int,
    message: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MEDIUM_PADDING),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        if(die1 != null){
            Image(
                modifier = Modifier
                    .size(SMALL_DIE_HEIGHT)
                    .weight(1f),
                painter = painterResource(id = die1),
                contentDescription = "Die One"
            )
        }
        else{
            Spacer(modifier = Modifier.weight(1f))
        }
        Image(
            modifier = Modifier
                .size(SMALL_DIE_HEIGHT)
                .weight(1f),
            painter = painterResource(id = die2),
            contentDescription = "Die Two"
        )
        Text(
            modifier = Modifier
                .weight(4f),
            text = message,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RuleRowPreview() {
    val dieOne: Int = R.drawable.one
    val dieTwo: Int = R.drawable.two
    val dieThree: Int = R.drawable.three
    val dieFour: Int = R.drawable.four
    val dieFive: Int = R.drawable.five
    val dieSix: Int = R.drawable.six
    RuleRow(Modifier,dieOne,dieTwo,"Test Rule!")
}