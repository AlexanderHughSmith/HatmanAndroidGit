package com.zanhsmitty.hatman

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
import com.zanhsmitty.hatman.ui.screens.how_to_play.HowToPlayScreen
import com.zanhsmitty.hatman.ui.screens.options.OptionsScreen
import com.zanhsmitty.hatman.ui.screens.setup.SetupScreen
import com.zanhsmitty.hatman.ui.screens.setup_play.SetupPlayScreen
import com.zanhsmitty.hatman.ui.theme.HatmanTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
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

    private val playButton = composeRule.onNodeWithContentDescription("Play Button")
    private val rulesButton = composeRule.onNodeWithContentDescription("Rules Button")
    private val addPlayerButton = composeRule.onNodeWithContentDescription("Add Player Button")
    private val startGameButton = composeRule.onNodeWithContentDescription("Lets Play Button")
    private val leaderBoard = composeRule.onNodeWithContentDescription("Leaderboard")
    private val rollDiceButton = composeRule.onNodeWithContentDescription("Roll Dice Button")
    private val backButton = composeRule.onNodeWithContentDescription("Back Button")
    private val aboutButton = composeRule.onNodeWithContentDescription("About Button")
    private val moreOptionsButton = composeRule.onNodeWithContentDescription("More Options Button")
    private fun getPlayerTextBox(index: Int) = composeRule.onNodeWithContentDescription("Player-$index-textField")
    private fun getPlayerRowByName(name: String) = composeRule.onNodeWithContentDescription("Player $name row")
    private fun waitForDiceToStopRolling() = composeRule.waitUntil(
        10000L
    ) {
        composeRule.onAllNodesWithText("Rolling...").fetchSemanticsNodes().isEmpty()
    }


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
                    composable(route = Screens.HowToPlay.route){
                        HowToPlayScreen(navController = navController)
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
    fun endToEndTest(){
        // Options Screen
        playButton.assertIsDisplayed()
        playButton.performClick()
        // Setup Screen
        addPlayerButton.assertIsDisplayed()
        addPlayerButton.performClick()
        getPlayerTextBox(5).performTextInput("Player 5")
        startGameButton.assertIsDisplayed()
        startGameButton.performClick()
        // Setup Play Screen
        leaderBoard.assertIsDisplayed()
        getPlayerRowByName("one").assertIsDisplayed()
        getPlayerRowByName("Player 5").assertIsDisplayed()
        rollDiceButton.performClick()
        waitForDiceToStopRolling()
        rollDiceButton.performClick()
        waitForDiceToStopRolling()
        Espresso.pressBack()
        // Options Screen
        playButton.assertIsDisplayed()
        playButton.performClick()
        // Setup Play Screen
        rollDiceButton.assertIsDisplayed()
        rollDiceButton.performClick()
        waitForDiceToStopRolling()
        Espresso.pressBack()
        // Options Screen
        rulesButton.assertIsDisplayed()
        rulesButton.performClick()
        // How To Play Screen
        composeRule.onNodeWithText("How To Play").assertIsDisplayed()
        backButton.performClick()
        // Options Screen
        aboutButton.assertIsDisplayed()
        aboutButton.performClick()
        // About Screen
        composeRule.onNodeWithText("Version").assertIsDisplayed()
        Espresso.pressBack()
        // Options Screen
        playButton.assertIsDisplayed()
        playButton.performClick()
        // Setup Play Screen
        rollDiceButton.performClick()
        waitForDiceToStopRolling()
        moreOptionsButton.assertExists()
        moreOptionsButton.performClick()
        composeRule.onNodeWithText("New Game").assertIsDisplayed()
        composeRule.onNodeWithText("New Game").performClick()
        // Setup Screen
        composeRule.waitUntil (10000L){
            composeRule.onAllNodesWithText("Setup").fetchSemanticsNodes().isNotEmpty()
        }
        Espresso.pressBack()
        // Options Screen
        playButton.assertIsDisplayed()
        playButton.performClick()
        // Setup Screen
        addPlayerButton.assertIsDisplayed()
        addPlayerButton.performClick()
        addPlayerButton.performClick()
        getPlayerTextBox(5).performTextInput("Player 5")
        getPlayerTextBox(6).performTextInput("six")
        startGameButton.assertIsDisplayed()
        startGameButton.performClick()
        // Setup Play Screen
        rollDiceButton.performClick()
        waitForDiceToStopRolling()
        getPlayerRowByName("six").assertIsDisplayed()
        moreOptionsButton.assertIsDisplayed()
        moreOptionsButton.performClick()
        composeRule.onNodeWithText("Rules").assertIsDisplayed()
        composeRule.onNodeWithText("Rules").performClick()
        // How To Play Screen
        composeRule.onNodeWithText("How To Play").assertIsDisplayed()
        Espresso.pressBack()
        // Setup Play Screen
        rollDiceButton.performClick()
        waitForDiceToStopRolling()
        moreOptionsButton.assertIsDisplayed()
        moreOptionsButton.performClick()
        composeRule.onNodeWithText("About").assertIsDisplayed()
        composeRule.onNodeWithText("About").performClick()
        // About Screen
        composeRule.onNodeWithText("Version").assertIsDisplayed()
        Espresso.pressBack()
        // Setup Play Screen
        rollDiceButton.performClick()
        waitForDiceToStopRolling()
    }
}