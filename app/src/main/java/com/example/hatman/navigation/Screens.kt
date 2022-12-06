package com.example.hatman.navigation

sealed class Screens(val route: String) {
    object Splash : Screens("splash_screen")
    object Welcome : Screens("welcome_screen")
    object Setup : Screens("setup_screen")
    object SetupPlay : Screens("setupPlay_screen/{people}") {
        fun passHeroId(people: Array<String>): String {
            return "details_screen/$people"
        }
    }

    object QuickPlay: Screens("quickPlay_screen")
    object Options: Screens("options_screen")
    object HowToPlay : Screens("how_to_play_screen")
    object About : Screens("about_screen")

    object Text : Screens("text_screen")
}
