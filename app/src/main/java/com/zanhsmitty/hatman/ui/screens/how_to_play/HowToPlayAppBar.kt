package com.zanhsmitty.hatman.ui.screens.how_to_play

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zanhsmitty.hatman.R

@ExperimentalMaterial3Api
@Composable
fun HowToPlayAppBar(
    navController: NavHostController
) {
    var navigate: Boolean by remember { mutableStateOf(false) }
    if(navigate){
        LaunchedEffect(Unit) {
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

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun SetupScreenAppBarPreview() {
    HowToPlayAppBar(
        rememberNavController()
    )
}