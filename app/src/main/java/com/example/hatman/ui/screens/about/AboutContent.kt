package com.example.hatman.ui.screens.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hatman.BuildConfig
import com.example.hatman.ui.theme.LARGE_PADDING

@Composable
fun AboutContent(
    modifier : Modifier,
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
    AboutContent(modifier = Modifier)
}