package com.zanhsmitty.hatman.ui.screens.options

import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zanhsmitty.hatman.MainActivity
import com.zanhsmitty.hatman.di.DataStoreModule
import com.zanhsmitty.hatman.di.DataStoreModuleTest
import com.zanhsmitty.hatman.di.DatabaseModule
import com.zanhsmitty.hatman.di.DatabaseModuleTest
import com.zanhsmitty.hatman.navigation.Screens
import com.zanhsmitty.hatman.navigation.SetupNavGraph
import com.zanhsmitty.hatman.ui.SharedViewModelTest
import com.zanhsmitty.hatman.ui.theme.HatmanTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@HiltAndroidTest
@UninstallModules(DatabaseModule::class, DataStoreModule::class)
class OptionsScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val composeRule = createComposeRule()


    @Before
    fun setup(){
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            val sharedViewModel = SharedViewModelTest(LocalContext.current)
            sharedViewModel.viewModel.setupFromMain()

            HatmanTheme(
                darkTheme = if(sharedViewModel.viewModel.darkTheme.value != null) sharedViewModel.viewModel.darkTheme.value!! else isSystemInDarkTheme(),
                dynamicColor = sharedViewModel.viewModel.useDynamicColors.value
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Screens.Options.route
                ){
                    composable(route = Screens.Options.route){
                        OptionsScreen(
                            navController = navController,
                            sharedViewModel = sharedViewModel.viewModel
                        )
                    }
                }
            }
        }
    }

    @Test
    fun testOptionsScreen(){
        composeRule.onNodeWithContentDescription("About Section").assertExists()
        composeRule.onNodeWithContentDescription("About Section").assertIsDisplayed()
        var b = 0
    }
}