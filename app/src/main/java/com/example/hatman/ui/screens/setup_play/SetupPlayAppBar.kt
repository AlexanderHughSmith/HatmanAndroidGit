package com.example.hatman.ui.screens.setup_play

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hatman.ui.SharedViewModel
import kotlin.text.Typography
import com.example.hatman.R
import com.example.hatman.data.HatmanDao
import com.example.hatman.data.HatmanRepository
import com.example.hatman.navigation.Screens
import com.example.hatman.ui.theme.LARGE_PADDING
import dagger.hilt.android.lifecycle.HiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupPlayAppBar(
    navController: NavHostController,
    onNewGameClicked:() -> Unit,
) {
    var navigate by remember {
        mutableStateOf(false)
    }
    if (navigate) {
        LaunchedEffect(key1 = Unit){
            onNewGameClicked()
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
            var expanded by remember { mutableStateOf(false) }

            IconButton(
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
                            navigate = true
                        }
                    )
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SetupScreenAppBarPreview() {
    SetupPlayAppBar(
        rememberNavController(),
        onNewGameClicked = {}
    )
}