package com.zanhsmitty.hatman.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.zanhsmitty.hatman.ui.theme.MEDIUM_PADDING
import com.zanhsmitty.hatman.ui.theme.SMALL_DIE_HEIGHT
import com.zanhsmitty.hatman.R


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
                .weight(4f)
                .padding(horizontal = MEDIUM_PADDING),
            text = message
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RuleRowPreview() {
    val dieOne: Int = R.drawable.one
    val dieTwo: Int = R.drawable.two
    RuleRow(Modifier,dieOne,dieTwo,"Test Rule!")
}