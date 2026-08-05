package com.darwin.ticketx.feature_ticket.presentation.components


import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp

@Composable
fun TicketQrDialog(
    qrBitmap: Bitmap,
    onDismiss: () -> Unit
) {

    AlertDialog(

        onDismissRequest = onDismiss,

        containerColor = MaterialTheme.colorScheme.surface,

        shape = RoundedCornerShape(32.dp),

        title = {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    imageVector = Icons.Default.ConfirmationNumber,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(42.dp)
                )


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                Text(
                    text = "Your Ticket",
                    style = MaterialTheme.typography.headlineSmall
                )


                Spacer(
                    modifier = Modifier.height(4.dp)
                )


                Text(
                    text = "Scan this QR code at the entrance",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },


        text = {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )
                ) {

                    Image(

                        bitmap = qrBitmap.asImageBitmap(),

                        contentDescription = "Ticket QR",

                        modifier = Modifier
                            .padding(20.dp)
                            .size(230.dp)

                    )
                }


                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )


                }
            }
        },


        confirmButton = {

            Button(

                onClick = onDismiss,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 8.dp
                    ),

                shape = RoundedCornerShape(16.dp)

            ) {

                Text(
                    text = "Close"
                )
            }
        }
    )
}