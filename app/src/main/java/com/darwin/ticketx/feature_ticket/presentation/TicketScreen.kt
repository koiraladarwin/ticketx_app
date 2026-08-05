package com.darwin.ticketx.feature_ticket.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.darwin.ticketx.core.utils.QrGenerator
import com.darwin.ticketx.feature_ticket.domain.model.TicketModel
import com.darwin.ticketx.feature_ticket.presentation.components.TicketQrDialog
import com.darwin.ticketx.feature_ticket.presentation.viewmodel.TicketViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketScreen(
    viewModel: TicketViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    var selectedTicketId by remember {
        mutableStateOf<String?>(null)
    }


    Scaffold(
        contentWindowInsets = WindowInsets(0),
        containerColor = MaterialTheme.colorScheme.background,

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "My Tickets",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }

    ) { padding ->


        when {

            state.isLoading -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator()
                }
            }


            state.error != null && state.tickets.isEmpty() -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = state.error ?: "Something went wrong",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }


            state.tickets.isEmpty() -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "No tickets available"
                    )
                }
            }


            else -> {


                val refreshState = rememberPullToRefreshState()


                PullToRefreshBox(
                    isRefreshing = state.isRefreshing,
                    onRefresh = {
                        viewModel.refresh()
                    },
                    state = refreshState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {


                    LazyColumn(

                        modifier = Modifier.fillMaxSize(),

                        contentPadding = PaddingValues(
                            start = 20.dp,
                            end = 20.dp,
                            top = 16.dp,
                            bottom = 24.dp
                        ),

                        verticalArrangement = Arrangement.spacedBy(18.dp)

                    ) {


                        items(
                            items = state.tickets,
                            key = {
                                it.id
                            }
                        ) { ticket ->


                            TicketCard(
                                ticket = ticket,
                                onClick = {

                                    selectedTicketId = ticket.id

                                }
                            )
                        }


                        item {

                            Spacer(
                                modifier = Modifier.navigationBarsPadding()
                            )
                        }
                    }
                }
            }
        }


        selectedTicketId?.let { ticketId ->


            val qrJson = """
                {
                  "ticket_id": "$ticketId"
                }
            """.trimIndent()


            val qrBitmap = remember(ticketId) {

                QrGenerator.generate(
                    content = qrJson
                )

            }


            TicketQrDialog(
                qrBitmap = qrBitmap,
                onDismiss = {
                    selectedTicketId = null
                }
            )
        }
    }
}
@Composable
fun TicketCard(
    ticket: TicketModel,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column {

            AsyncImage(
                model = ticket.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = ticket.eventTitle,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(12.dp))

                TicketInfoRow(
                    Icons.Default.ConfirmationNumber,
                    ticket.ticketType
                )

                Spacer(modifier = Modifier.height(8.dp))

                TicketInfoRow(
                    Icons.Default.LocationOn,
                    ticket.venue
                )

                Spacer(modifier = Modifier.height(8.dp))

                TicketInfoRow(
                    Icons.Default.CalendarMonth,
                    ticket.eventDate
                )

                Spacer(modifier = Modifier.height(18.dp))

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outline.copy(.35f)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Price",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = ticket.price,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                }

            }

        }

    }

}
@Composable
fun TicketInfoRow(
    icon: ImageVector,
    text: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

    }

}
val sampleTickets = listOf(

    TicketModel(
        id = "1",
        image = "https://picsum.photos/600/400?1",
        eventTitle = "Kathmandu Music Festival 2026",
        ticketType = "VIP Pass",
        venue = "Bhrikutimandap",
        eventDate = "24 Aug 2026 • 7:00 PM",
        price = "Rs. 2,499"
    ),

    TicketModel(
        id = "2",
        image = "https://picsum.photos/600/400?2",
        eventTitle = "Tech Summit Nepal",
        ticketType = "Business Class",
        venue = "Hotel Yak & Yeti",
        eventDate = "12 Sept 2026 • 10:00 AM",
        price = "Rs. 1,299"
    )

)