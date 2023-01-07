package com.zanhsmitty.hatman.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zanhsmitty.hatman.ui.theme.MEDIUM_PADDING

@Composable
fun DetailCard(modifier:Modifier, title:String, description:String, extra: @Composable () -> Unit) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MEDIUM_PADDING),
            horizontalArrangement  =  Arrangement.SpaceBetween
        ){
            Column{
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            extra()
        }
    }
}

@Preview
@Composable
fun DetailCardPreview() {
    DetailCard(modifier = Modifier, title = "Title is here", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc sit amet ultricies ultricies, nisl ") {

    }
}