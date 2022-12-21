package com.zanhsmitty.hatman

import android.os.Looper
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.Espresso
import com.zanhsmitty.hatman.di.DataStoreModule
import com.zanhsmitty.hatman.di.DatabaseModule
import com.zanhsmitty.hatman.navigation.Screens
import com.zanhsmitty.hatman.ui.SharedViewModelTest
import com.zanhsmitty.hatman.ui.screens.about.AboutScreen
import com.zanhsmitty.hatman.ui.screens.options.OptionsScreen
import com.zanhsmitty.hatman.ui.screens.setup.SetupScreen
import com.zanhsmitty.hatman.ui.screens.setup_play.SetupPlayScreen
import com.zanhsmitty.hatman.ui.theme.HatmanTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@HiltAndroidTest
@UninstallModules(DatabaseModule::class, DataStoreModule::class)
class HatmanEndToEndTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val composeRule = createComposeRule()


    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            val sharedViewModel = SharedViewModelTest(LocalContext.current)
            sharedViewModel.viewModel.setupFromMain()

            HatmanTheme(
                darkTheme = if (sharedViewModel.viewModel.darkTheme.value != null) sharedViewModel.viewModel.darkTheme.value!! else isSystemInDarkTheme(),
                dynamicColor = sharedViewModel.viewModel.useDynamicColors.value
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Screens.Options.route
                ) {
                    composable(route = Screens.Options.route) {
                        OptionsScreen(
                            navController = navController,
                            sharedViewModel = sharedViewModel.viewModel
                        )
                    }
                    composable(route = Screens.About.route) {
                        AboutScreen(
                            navController = navController,
                            sharedViewModel = sharedViewModel.viewModel
                        )
                    }
                    composable(route = Screens.Setup.route){
                        SetupScreen(
                            navController = navController,
                            sharedViewModel = sharedViewModel.viewModel
                        )
                    }
                    composable(route = Screens.SetupPlay.route){
                        SetupPlayScreen(
                            navController = navController,
                            sharedViewModel = sharedViewModel.viewModel
                        )
                    }
                }
            }
        }
    }

    @Test
    fun Navigate_to_about_page_and_press_the_back_button(){
        composeRule.onNodeWithContentDescription("About Button").assertExists()
        composeRule.onNodeWithContentDescription("About Button").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("About Button").performClick()
        composeRule.onNodeWithContentDescription("Color Theme Card").assertExists()
        composeRule.onNodeWithContentDescription("Color Theme Card").assertIsDisplayed()
        Espresso.pressBack()
        composeRule.onNodeWithContentDescription("About Button").assertExists()
        composeRule.onNodeWithContentDescription("About Button").assertIsDisplayed()
    }

    @Test
    fun Navigate_to_play_page_create_5_players_assert_leaderboard_works(){
        composeRule.onNodeWithContentDescription("Play Button").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Play Button").performClick()
        composeRule.onNodeWithContentDescription("Add Player Button").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Add Player Button").performClick()
        composeRule.onNodeWithContentDescription("Player-5-textField").performTextInput("Player 5")
        Espresso.pressBack()
        composeRule.onNodeWithContentDescription("Lets Play Button").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Lets Play Button").performClick()
        composeRule.onNodeWithContentDescription("Leaderboard").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Player one row").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Player Player 5 row").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("More Options Button").assertExists()
        composeRule.onNodeWithContentDescription("Roll Dice Button").performClick()
        composeRule.onNodeWithContentDescription("Die One").assertIsDisplayed()
        //assert(composeRule.onAllNodesWithText("Rolling...").fetchSemanticsNodes().isNotEmpty())
        composeRule.waitUntil(
            10000L
        ) {
            composeRule.onAllNodesWithText("Rolling...").fetchSemanticsNodes().isEmpty()
        }
        composeRule.onNodeWithContentDescription("Roll Dice Button").performClick()
        composeRule.waitUntil(
            10000L
        ) {
            composeRule.onAllNodesWithText("Rolling...").fetchSemanticsNodes().isEmpty()
        }
        composeRule.onNodeWithContentDescription("Roll Dice Button").performClick()
    }
}