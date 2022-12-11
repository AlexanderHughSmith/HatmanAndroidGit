package com.zanhsmitty.hatman.ui.screens.options

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zanhsmitty.hatman.R
import com.zanhsmitty.hatman.navigation.Screens

@ExperimentalMaterial3Api
@Composable
fun SetupScreenAppBar(
    navController: NavHostController
) {
    var navigate: Boolean by remember { mutableStateOf(false) }
    if(navigate){
        LaunchedEffect(Unit) {
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
    )
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun SetupScreenAppBarPreview() {
    SetupScreenAppBar(
        rememberNavController()
    )
}