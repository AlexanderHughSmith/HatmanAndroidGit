package com.zanhsmitty.hatman.ui.screens.setup

import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zanhsmitty.hatman.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupAppBar(
    navController: NavHostController,
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
        title = {
            Text(
                text = "Setup",
                color = MaterialTheme.colorScheme.primary
            )
        },
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
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SetupScreenAppBarPreview() {
    SetupAppBar(
        rememberNavController()
    )
}