package com.example.hatman.ui.screens.setup

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