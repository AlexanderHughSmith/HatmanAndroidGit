package com.zanhsmitty.hatman.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.zanhsmitty.hatman.data.model.Player
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import com.zanhsmitty.hatman.ui.theme.*
import com.zanhsmitty.hatman.util.Constants.GET_FAKE_PLAYERS
import com.zanhsmitty.hatman.R


@Composable
fun LeaderBoard(
    playerList: List<Player>,
    color: Color
) {
    LeaderBoardView(playerList = playerList, color = color)
}

@Composable
fun LeaderBoardView(
    playerList: List<Player>,
    color: Color
) {
    Surface(
        modifier = Modifier
            .semantics { contentDescription = "Leaderboard" }
            .padding(LARGE_PADDING),
        shape = MaterialTheme.shapes.medium,
        color = color,
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            for(player in playerList){
                LeaderBoardRow(
                    player = player
                )
            }
        }
    }
}

@Composable
fun LeaderBoardRow(
    player: Player
) {
    Row(
        modifier = Modifier
            .semantics {
                contentDescription = "Player ${player.name} row"
            }
            .fillMaxWidth()
            .padding(MEDIUM_PADDING),
        verticalAlignment = Alignment.Bottom,
    ){
        if(player.isHatman){
            Icon(
                modifier = Modifier
                    .weight(1f)
                    .size(HATMAN_ICON_SIZE),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Hatman"
            )
        }
        else{
            Spacer(modifier = Modifier.weight(1f))
        }
        Text(
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = SMALL_PADDING),
            text = player.name,
            fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
            fontWeight = FontWeight.Bold
        )
        if(player.isRolling){
            Icon(
                modifier = Modifier
                    .weight(1f)
                    .size(HATMAN_ICON_SIZE),
                painter = painterResource(id = R.drawable.dice_solid),
                contentDescription = "Rolling"
            )
        }
        else{
            Spacer(modifier = Modifier.weight(1f))
        }
        Icon(
            modifier = Modifier
                .weight(1f)
                .size(BEER_ICON_SIZE),
            painter = painterResource(id = R.drawable.beer),
            contentDescription = "Drinks Taken Icon"
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = SMALL_PADDING),
            text = player.score.toString(),
            fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LeaderBoardRowPreview() {
    LeaderBoardRow(
        GET_FAKE_PLAYERS()[0]
    )
}

@Preview(showBackground = true)
@Composable
fun LeaderBoardPreview() {
    LeaderBoardView(
        GET_FAKE_PLAYERS(),
        MaterialTheme.colorScheme.primary
    )
}
