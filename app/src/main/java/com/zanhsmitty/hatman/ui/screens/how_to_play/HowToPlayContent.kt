package com.zanhsmitty.hatman.ui.screens.how_to_play

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zanhsmitty.hatman.ui.components.RuleRow
import com.zanhsmitty.hatman.ui.theme.LARGEST_PADDING
import com.zanhsmitty.hatman.ui.theme.LARGE_PADDING
import com.zanhsmitty.hatman.R


@Composable
fun HowToPlayContent(
    modifier : Modifier,
) {
    val dieOne: Int = R.drawable.one
    val dieTwo: Int = R.drawable.two
    val dieThree: Int = R.drawable.three
    val dieFour: Int = R.drawable.four
    val dieFive: Int = R.drawable.five
    val dieSix: Int = R.drawable.six
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item {
            DividingHeader("Basic Rules")
        }
        item {
            BasicRuleRow("We will randomly select one player to be the hatman and another player who will go first. (Not the same player).")
        }
        item {
            BasicRuleRow("The player, who we selected at random to go first, will role the dice until there role results in no one taking a drink.")
        }
        item {
            BasicRuleRow("When this happens, the players turn will end and the next player (to the left) will start their turn.")
        }
        item {
            BasicRuleRow("Note: The hatman can not roll the dice. They will be skipped and the next player will begin their turn.")
        }
        item {
            DividingHeader("Dice Rules")
        }
        item {
            RuleRow(modifier = Modifier, die1 = dieOne, die2 = dieTwo, message = "The dice add up to 3, the player who roles this becomes the hatman. Their turn ends.")
        }
        item {
            RuleRow(modifier = Modifier, die1 = dieThree, die2 = dieSix, message = "The dice add up to 9, the player behind the person who rolled takes a drink. (9 behind)")
        }
        item {
            RuleRow(modifier = Modifier, die1 = dieFour, die2 = dieThree, message = "The dice add up to 7, the player ahead of the person who rolled takes a drink. (7 ahead)")
        }
        item {
            RuleRow(modifier = Modifier, die1 = null, die2 = dieThree, message = "Any time a 3 is rolled, the hatman takes a drink.")
        }
        item {
            RuleRow(modifier = Modifier, die1 = dieFour, die2 = dieSix, message = "The dice add up to 10, All players take a drink. (Community)")
        }
        item {
            RuleRow(modifier = Modifier, die1 = dieFour, die2 = dieFour, message = "Doubles, a random player (not the current player) takes as many drinks as the number on one of the die. (Doubles)")
        }
        item {
            BasicRuleRow("Note: These can be stacked.")
        }
        item{
            DividingHeader(message = "Some Examples")
        }
        item {
            RuleRow(modifier = Modifier, die1 = dieThree, die2 = dieFour, message = "3 - hatman takes a drink.\n7 - player ahead takes a drink.")
        }
        item {
            RuleRow(modifier = Modifier, die1 = dieFive, die2 = dieFive, message = "Doubles - random player takes 5 drinks.\n10 - Everyone takes one drink.")
        }
        item {
            RuleRow(modifier = Modifier, die1 = dieThree, die2 = dieThree, message = "3 - hatman takes 2 drinks.\nDoubles - random player takes 3 drinks.")
        }
        item {
            RuleRow(modifier = Modifier, die1 = dieTwo, die2 = dieFour, message = "Nobody drinks.\nThis players turn ends.")
        }
    }
}

@Composable
fun DividingHeader(
    message: String
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LARGE_PADDING),
        color = MaterialTheme.colorScheme.tertiary,
        text = message,
    )
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start= LARGE_PADDING, end = LARGEST_PADDING),
        color = Color.Gray,
        thickness = 1.dp
    )
}

@Composable
fun BasicRuleRow(
    message: String
) {
    val bullet = "\u2022"
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LARGE_PADDING),
        text = "$bullet\t$message"
    )
}

//preview for HotToPlayContent
@Preview(showBackground = true)
@Composable
fun HowToPlayContentPreview() {
    HowToPlayContent(modifier = Modifier)
}