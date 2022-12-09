package com.example.hatman.ui.screens.about

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.RadioGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.ContextCompat.getSystemService
import com.example.hatman.BuildConfig
import com.example.hatman.R
import com.example.hatman.ui.SharedViewModel
import com.example.hatman.ui.theme.LARGE_PADDING
import com.google.android.material.button.MaterialButtonToggleGroup
import java.security.AccessController.getContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutContent(
    modifier : Modifier,
    sharedViewModel: SharedViewModel,
) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item {
            BasicTextRow("Version: ${BuildConfig.VERSION_NAME}")
        }
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("Feedback Email:")
                EmailAddress("HatmanFeedback@protonmail.com", LocalContext.current)
            }
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LARGE_PADDING),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("Use dynamic colors ")
                    Checkbox(checked = sharedViewModel.useDynamicColors.value, onCheckedChange = {
                        sharedViewModel.useDynamicColors.value = it
                        sharedViewModel.updateDynamicColors()
                    })
                }

            }
        }
        /*item{
            val options = listOf("System", "Dark", "Light")
            var expanded by remember { mutableStateOf(false) }
            var selectedOptionText by remember { mutableStateOf(options[0]) }
            selectedOptionText = when(sharedViewModel.darkTheme.value){
                null -> options[0]
                true -> options[1]
                false -> options[2]
            }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                },
            ) {
                TextField(
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = { },
                    label = { Text("Color Theme") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                                when(selectionOption){
                                    "System" -> sharedViewModel.darkTheme.value = null
                                    "Dark" -> sharedViewModel.darkTheme.value = true
                                    "Light" -> sharedViewModel.darkTheme.value = false
                                }
                            },
                            text = { Text(selectionOption) }
                        )
                    }
                }
            }
        }*/
        item{
            Text("Color Theme")
            val radioOptions = listOf("System", "Dark", "Light")
            val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
            // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
            Column(Modifier.selectableGroup()) {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth(.4f)
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
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = null // null recommended for accessibility with screenreaders
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmailAddress(email: String, context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Text(
            modifier = Modifier.weight(5f),
            text = email,
            textAlign = TextAlign.Center
        )
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {
                // Copy the email address to the clipboard
                copyToClipboard(email, context)
            },
        ){
            Icon(
                painter = painterResource(id = R.drawable.copy),
                contentDescription = "Copy email address to clipboard"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmailAddressPreview() {
    EmailAddress("HatmanFeedback@protonmail.com", LocalContext.current)
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