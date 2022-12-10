package com.zanhsmitty.hatman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.zanhsmitty.hatman.navigation.SetupNavGraph
import com.zanhsmitty.hatman.ui.SharedViewModel
import com.zanhsmitty.hatman.ui.theme.HatmanTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel.setupFromMain(this)
        setContent {
            HatmanTheme(
                darkTheme = if(sharedViewModel.darkTheme.value != null) sharedViewModel.darkTheme.value!! else isSystemInDarkTheme(),
                dynamicColor = sharedViewModel.useDynamicColors.value
            ) {
                navController = rememberAnimatedNavController()
                SetupNavGraph(
                    navController = navController,
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HatmanTheme {
        Greeting("Android")
    }
}