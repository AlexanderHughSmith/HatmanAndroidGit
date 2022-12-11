package com.zanhsmitty.hatman.util

import com.zanhsmitty.hatman.data.model.Player

object Constants {

    const val DATABASE_TABLE = "player_table"
    const val DATABASE_NAME = "hatman_database"

    const val MIN_PLAYERS = 3
    const val MAX_PLAYERS = 8

    const val MAX_TITLE_LENGTH = 20
    const val SPLASH_SCREEN_DELAY = 3000L

    const val NORMAL_ANIMATION_SPEED = 300
    const val FAST_ANIMATION_SPEED = 100
    const val SLOW_ANIMATION_SPEED = 500

    fun GET_FAKE_PLAYERS():List<Player>{
        return listOf(
            Player(0, "Player 0", 0, false, false),
            Player(1, "Player 1", 0, true, false),
            Player(2, "Player 2", 0, false, false),
            Player(3, "Player 3", 0, false, true)
        )
    }

}