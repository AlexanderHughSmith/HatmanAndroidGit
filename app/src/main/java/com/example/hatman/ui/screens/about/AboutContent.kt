package com.example.hatman.ui.screens.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hatman.BuildConfig
import com.example.hatman.ui.SharedViewModel
import com.example.hatman.ui.theme.LARGE_PADDING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutContent(
    modifier : Modifier,
    sharedViewModel: SharedViewModel,
) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        item {
            BasicTextRow("Version: ${BuildConfig.VERSION_NAME}")
        }
        item {
            BasicTextRow("Feedback Email: HatmanFeedback@protonmail.com")
        }
        item {
            BasicTextRow("App created by: Alexander Smith")
        }
        item {
            Checkbox(checked = sharedViewModel.useDynamicColors.value, onCheckedChange = {
                sharedViewModel.useDynamicColors.value = it
            })
        }
        item{
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
        }
    }
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