
package com.example.hatman.ui.screens.splash

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hatman.ui.theme.LOGO_HEIGHT
import com.example.hatman.util.Constants.SPLASH_SCREEN_DELAY
import kotlinx.coroutines.delay
import com.example.hatman.R
import com.example.hatman.ui.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navigateToListScreen: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    var navigate: Boolean by remember { mutableStateOf(false) }
    if(navigate){
        LaunchedEffect(Unit) {
            navigateToListScreen()
        }
    }
    //Log.d("OptionsScreen", "SplashScreen")
    var startAnimation by remember { mutableStateOf(false) }
    val offsetState by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else 100.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    val alphaState by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        startAnimation = true
        //sharedViewModel.getAllPlayers()

        //sharedViewModel.getAllPlayers();
        sharedViewModel.setupFromSplash()

        /*sharedViewModel.setRoles()
        sharedViewModel.saveChanges()
        sharedViewModel.setupDataStore(context)*/
        //sharedViewModel.getAllPlayers()
        delay(SPLASH_SCREEN_DELAY)
        navigate = true
    }

    Splash(offsetState = offsetState, alphaState = alphaState)
}

@Composable
fun Splash(offsetState: Dp, alphaState: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(LOGO_HEIGHT)
                .offset(y = offsetState)
                .alpha(alpha = alphaState),
            painter = painterResource(id = getLogo()),
            contentDescription = "To Do Logo",
            tint = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Composable
fun getLogo(): Int {
    return if (isSystemInDarkTheme()) {
        R.drawable.ic_logo
    } else {
        R.drawable.ic_logo
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(offsetState = 0.dp, alphaState = 1f)
}
