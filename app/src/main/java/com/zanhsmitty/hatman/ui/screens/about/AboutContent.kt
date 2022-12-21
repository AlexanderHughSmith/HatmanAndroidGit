package com.zanhsmitty.hatman.ui.screens.about

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.zanhsmitty.hatman.BuildConfig
import com.zanhsmitty.hatman.R
import com.zanhsmitty.hatman.ui.SharedViewModel
import com.zanhsmitty.hatman.ui.components.DetailCard
import com.zanhsmitty.hatman.ui.theme.LARGEST_PADDING
import com.zanhsmitty.hatman.ui.theme.LARGE_PADDING
import com.zanhsmitty.hatman.ui.theme.MEDIUM_PADDING

@ExperimentalMaterial3Api
@Composable
fun AboutContent(
    modifier : Modifier,
    sharedViewModel: SharedViewModel,
) {
    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }
    if(showDialog.value){
        AlertDialog(
            modifier = Modifier.semantics {
              contentDescription = "Theme Dialog"
            },
            onDismissRequest = { showDialog.value = false },
            title = { Text("Color Theme") },
            text = {
                   Divider()
                val radioOptions = listOf("System", "Dark", "Light")
                val selectedIndex = when(sharedViewModel.darkTheme.value){
                    null -> 0
                    true -> 1
                    false -> 2
                }
                val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[selectedIndex]) }
                // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
                Column(Modifier.selectableGroup()) {
                    radioOptions.forEach { text ->
                        Row(
                            Modifier
                                .semantics {
                                    contentDescription = "Theme-${text}"
                                }
                                .fillMaxWidth()
                                .height(56.dp)
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = {
                                        onOptionSelected(text)
                                        when (text) {
                                            "System" -> sharedViewModel.darkTheme.value = null
                                            "Dark" -> sharedViewModel.darkTheme.value = true
                                            "Light" -> sharedViewModel.darkTheme.value = false
                                        }
                                        sharedViewModel.updateDarkTheme()
                                    },
                                    role = Role.RadioButton
                                ),
                                //.padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = null // null recommended for accessibility with screen readers
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }
                   },
            dismissButton = {
                Divider()
                Button(
                    modifier = Modifier.semantics {
                      contentDescription = "Dismiss"
                    },
                    onClick = { showDialog.value = false }
                ) {
                    Text("OK")
                }
            },
            confirmButton = {
            },
        )
    }

    LazyColumn(
        modifier = modifier.padding(horizontal = LARGE_PADDING),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item {
            DetailCard(
                modifier = Modifier,
                title = "Version",
                description = BuildConfig.VERSION_NAME,
            ) {}
        }
        item {
            DetailCard(
                modifier = Modifier,
                title = "Feedback",
                description = "HatmanFeedback@protonmail.com",
                extra = {
                    IconButton(
                        onClick = {
                            // Copy the email address to the clipboard
                            copyToClipboard("HatmanFeedback@protonmail.com", context)
                        },
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.copy),
                            contentDescription = "Copy email address to clipboard"
                        )
                    }
                }
            )
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            item {
                DetailCard(
                    modifier = Modifier,
                    title = "Dynamic colors",
                    description = "Use dynamic colors for the app",
                ) {
                    Checkbox(checked = sharedViewModel.useDynamicColors.value, onCheckedChange = {
                        sharedViewModel.useDynamicColors.value = it
                        sharedViewModel.updateDynamicColors()
                    })
                }
            }
        }
        item{
            val angle: Float by animateFloatAsState(
                targetValue = if (showDialog.value) 180f else 0f
            )
            val radioOptions = listOf("System", "Dark", "Light")
            val selectedIndex = when(sharedViewModel.darkTheme.value){
                null -> 0
                true -> 1
                false -> 2
            }
            DetailCard(
                modifier = Modifier,
                title = "Color Theme",
                description = "The color theme of the app",
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight()
                        .clickable { showDialog.value = true }
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.5f
                            ),
                            shape = MaterialTheme.shapes.small
                        )
                        .semantics {
                            contentDescription = "Color Theme Card"
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .weight(4f),
                        text=radioOptions[selectedIndex],
                    )
                    IconButton(
                        modifier = Modifier
                            .rotate(degrees = angle)
                            .weight(weight = 1f)
                            .semantics {
                                       contentDescription = "Open color theme dialog"
                            },
                        onClick = { showDialog.value = true }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Dropdown arrow"
                        )
                    }
                }
            }
        }
    }
}

fun copyToClipboard(text: String, context: Context) {
    val clipboard = ContextCompat.getSystemService(context,ClipboardManager::class.java)
    clipboard?.setPrimaryClip(ClipData.newPlainText("",text))
}

@Composable
fun BasicTextRow(
    message: String
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LARGE_PADDING),
        text = message,
        textAlign = TextAlign.Center
    )
}

//preview for HotToPlayContent
@Preview(showBackground = true)
@Composable
fun HowToPlayContentPreview() {
    //AboutContent(modifier = Modifier, null)
}