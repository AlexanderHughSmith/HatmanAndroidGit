package com.zanhsmitty.hatman.ui.screens.setup_play

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zanhsmitty.hatman.R
import com.zanhsmitty.hatman.navigation.Screens
import com.zanhsmitty.hatman.ui.theme.LARGE_PADDING

@ExperimentalMaterial3Api
@Composable
fun SetupPlayAppBar(
    navController: NavHostController,
    onNewGameClicked:() -> Unit,
) {
    var navigate: Int by remember { mutableStateOf(0) }
    if(navigate != 0){
        LaunchedEffect(Unit) {
            when (navigate) {
                1 -> {
                    onNewGameClicked()
                }
                2 -> {
                    navController.navigate(Screens.About.route)
                }
                3 -> {
                    navController.popBackStack()
                }
                4 -> {
                    navController.navigate(Screens.HowToPlay.route)
                }
            }
        }
    }
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Hatman",
                color = MaterialTheme.colorScheme.primary
            )
        },
        navigationIcon = {
            IconButton(onClick = { navigate = 3 }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Back"
                )
            }
        },

        actions = {
            var expanded by remember { mutableStateOf(false) }

            IconButton(
                modifier = Modifier.semantics { contentDescription = "More Options Button" },
                onClick = { expanded = true }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_vertical_menu),
                    contentDescription = "temp",
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                modifier = Modifier
                                    .padding(start = LARGE_PADDING),
                                text = "New Game",
                            )
                       },
                        onClick = {
                            expanded = false
                            navigate = 1
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                modifier = Modifier
                                    .padding(start = LARGE_PADDING),
                                text = "Rules",
                            )
                        },
                        onClick = {
                            expanded = false
                            navigate = 4
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                modifier = Modifier
                                    .padding(start = LARGE_PADDING),
                                text = "About",
                            )
                        },
                        onClick = {
                            expanded = false
                            navigate = 2
                        }
                    )
                }
            }
        },
    )
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun SetupScreenAppBarPreview() {
    SetupPlayAppBar(
        rememberNavController(),
        onNewGameClicked = {}
    )
}