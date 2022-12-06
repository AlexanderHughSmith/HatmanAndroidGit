package com.example.hatman.ui.screens.how_to_play

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hatman.R
import com.example.hatman.navigation.Screens
import com.example.hatman.ui.theme.LARGE_PADDING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HowToPlayAppBar(
    navController: NavHostController
) {
    var navigate: Boolean by remember { mutableStateOf(false) }
    if(navigate){
        LaunchedEffect(Unit) {
            //navController.popBackStack(Screens.Options.route, false)
            //navController.navigate(Screens.About.route)
            navController.popBackStack()
        }
    }
    CenterAlignedTopAppBar(
        //Button to go back to the previous screen
        navigationIcon = {
            IconButton(
                onClick = { navigate = true }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Back"
                )
            }
        },
        title = {
            Text(
                text = "How To Play",
                color = MaterialTheme.colorScheme.primary
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SetupScreenAppBarPreview() {
    HowToPlayAppBar(
        rememberNavController()
    )
}