package com.example.hatman.ui.screens.options

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
fun SetupScreenAppBar(
    navController: NavHostController
) {
    var navigate: Boolean by remember { mutableStateOf(false) }
    if(navigate){
        LaunchedEffect(Unit) {
            //navController.popBackStack(Screens.Options.route, false)
            navController.navigate(Screens.About.route)
        }
    }
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Hatman",
                color = MaterialTheme.colorScheme.primary
            )
        },
        actions = {
            IconButton(
                onClick = {
                    navigate = true
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.info),
                    contentDescription = "About Section",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        /*colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),*/
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SetupScreenAppBarPreview() {
    SetupScreenAppBar(
        rememberNavController()
    )
}