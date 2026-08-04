package com.darwin.ticketx.feature_event_details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.darwin.ticketx.feature_ticket.presentation.components.TicketBottomSheet
import com.darwin.ticketx.feature_ticket.presentation.eventDetailsSample


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun EventDetailsScreen(
    eventId: String,
    onBack: () -> Unit
) {
    val event = eventDetailsSample
    var showTicketSheet by remember {
        mutableStateOf(false)
    }


    Scaffold(
        contentWindowInsets = WindowInsets(0),
        bottomBar = {

            Surface(
                shadowElevation = 8.dp
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 14.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "Starting from",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )


                        Text(
                            text = "Rs ${event.ticketTypes.minOf { it.price }}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                    }


                    Button(
                        onClick = {
                            showTicketSheet = true
                        },
                        shape = RoundedCornerShape(18.dp)
                    ) {

                        Text(
                            "Buy Ticket"
                        )

                    }

                }

            }

        }

    ) { padding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {


            Box {


                AsyncImage(

                    model = event.banner,

                    contentDescription = null,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),

                    contentScale = ContentScale.Crop
                )


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = .55f)
                                )
                            )
                        )
                )


                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),

                    horizontalArrangement = Arrangement.SpaceBetween

                ) {


                    IconButton(

                        modifier = Modifier
                            .background(
                                Color.Black.copy(.35f),
                                CircleShape
                            ),

                        onClick = onBack

                    ) {

                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )

                    }


                    IconButton(

                        modifier = Modifier
                            .background(
                                Color.Black.copy(.35f),
                                CircleShape
                            ),

                        onClick = {}

                    ) {

                        Icon(
                            Icons.Default.Share,
                            contentDescription = null,
                            tint = Color.White
                        )

                    }

                }

            }



            Column(

                modifier = Modifier
                    .padding(20.dp)

            ) {


                Surface(

                    color = MaterialTheme.colorScheme.primary.copy(.12f),

                    shape = RoundedCornerShape(50)

                ) {

                    Text(

                        text = event.category.uppercase(),

                        modifier = Modifier.padding(
                            horizontal = 14.dp,
                            vertical = 6.dp
                        ),

                        color = MaterialTheme.colorScheme.primary,

                        style = MaterialTheme.typography.labelMedium,

                        fontWeight = FontWeight.Bold
                    )

                }



                Spacer(
                    Modifier.height(12.dp)
                )


                Text(

                    text = event.title,

                    style = MaterialTheme.typography.headlineMedium,

                    fontWeight = FontWeight.Bold

                )



                Spacer(
                    Modifier.height(18.dp)
                )


                InfoCard(
                    icon = {
                        Icon(
                            Icons.Default.CalendarMonth,
                            null
                        )
                    },
                    text = event.date
                )


                Spacer(
                    Modifier.height(10.dp)
                )


                InfoCard(
                    icon = {
                        Icon(
                            Icons.Default.LocationOn,
                            null
                        )
                    },
                    text = event.location
                )



                Spacer(
                    Modifier.height(24.dp)
                )


                Text(

                    text = "About Event",

                    style = MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.Bold

                )


                Spacer(
                    Modifier.height(8.dp)
                )


                Text(

                    text = event.description,

                    style = MaterialTheme.typography.bodyLarge,

                    color = MaterialTheme.colorScheme.onSurfaceVariant

                )



                Spacer(
                    Modifier.height(24.dp)
                )



                Text(

                    text = "Tags",

                    style = MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.Bold

                )


                Spacer(
                    Modifier.height(12.dp)
                )


                Row(

                    horizontalArrangement = Arrangement.spacedBy(8.dp)

                ) {

                    event.tags.forEach { tag ->

                        AssistChip(

                            onClick = {},

                            label = {
                                Text(tag)
                            }

                        )

                    }

                }


                Spacer(
                    Modifier.height(100.dp)
                )

            }

        }

    }



    if(showTicketSheet){

        TicketBottomSheet(
            event = event,
            onDismiss = {
                showTicketSheet = false
            },
            onPurchase = { ticket ->

                // later:
                // call API here

                showTicketSheet = false
            }
        )

    }

}



@Composable
private fun InfoCard(
    icon: @Composable () -> Unit,
    text: String
){

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(18.dp)
            )
            .padding(14.dp),

        verticalAlignment = Alignment.CenterVertically

    ){

        icon()

        Spacer(
            Modifier.width(12.dp)
        )

        Text(text)

    }

}