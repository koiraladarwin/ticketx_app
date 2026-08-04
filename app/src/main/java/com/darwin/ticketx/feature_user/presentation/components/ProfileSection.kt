package com.darwin.ticketx.feature_user.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileSection(
    title: String,
    content: @Composable () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {


        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    bottom = 8.dp
                )
        )


        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            tonalElevation = 1.dp
        ) {

            Column {

                content()

            }

        }

    }

}