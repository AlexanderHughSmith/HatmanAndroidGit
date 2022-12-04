package com.example.hatman.util

import com.example.hatman.data.model.Player

object Constants {

    const val DATABASE_TABLE = "player_table"
    const val DATABASE_NAME = "hatman_database"

    const val SPLASH_SCREEN = "splash"
    const val MAIN_SCREEN = "main"
    const val SETUP_PLAY_SCREEN = "main"
    const val SETUP_SCREEN = "setup"

    const val PEOPLE_ARGUMENT_KEY = "people"

    const val LIST_ARGUMENT_KEY = "action"
    const val TASK_ARGUMENT_KEY = "taskId"

    const val PREFERENCE_NAME = "todo_preferences"
    const val PREFERENCE_KEY = "sort_state"

    const val MIN_PLAYERS = 3
    const val MAX_PLAYERS = 8

    const val MAX_TITLE_LENGTH = 20
    const val SPLASH_SCREEN_DELAY = 3000L

    fun GET_FAKE_PLAYERS():List<Player>{
        return listOf(
            Player(0, "Player 0", 0, false, false),
            Player(1, "Player 1", 0, true, false),
            Player(2, "Player 2", 0, false, false),
            Player(3, "Player 3", 0, false, true)
        )
    }

}