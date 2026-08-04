package com.darwin.ticketx.feature_ticket.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.darwin.ticketx.feature_ticket.presentation.components.EventUi
import com.darwin.ticketx.feature_ticket.presentation.components.FeaturedCarousel

@Composable
fun HomeScreen(
    events: List<EventUi>,
    onEventClick: (EventUi) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 12.dp,
            end = 12.dp,
            top = 16.dp,
            bottom = 24.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        item(
            span = { GridItemSpan(2) }
        ) {

            FeaturedCarousel(
                events = events.take(6),
                onClick = onEventClick
            )

        }

        item(
            span = { GridItemSpan(2) }
        ) {

            Text(
                text = "Upcoming Events",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

        }

        items(
            events,
            key = { it.id }
        ) { event ->

            EventCard(
                event = event,
                onClick = {
                    onEventClick(event)
                }
            )

        }

        item(
            span = { GridItemSpan(2) }
        ) {
            Spacer(
                modifier = Modifier.navigationBarsPadding()
            )
        }

    }

}
val sampleEvents = listOf(

    EventUi(
        "1",
        "Coldplay Live",
        "Kathmandu",
        "25 Aug",
        "https://picsum.photos/600/800"
    ),

    EventUi(
        "2",
        "Imagine Dragons",
        "Pokhara",
        "2 Sept",
        "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=1200"
    ),

    EventUi(
        "3",
        "Taylor Swift",
        "Lalitpur",
        "11 Sept",
        "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?w=1200"
    ),

    EventUi(
        "4",
        "Tech Summit",
        "Bhaktapur",
        "15 Sept",
        "https://images.unsplash.com/photo-1511578314322-379afb476865?w=1200"
    ),

    EventUi(
        "5",
        "Football Finals",
        "Kathmandu Stadium",
        "18 Sept",
        "https://images.unsplash.com/photo-1517466787929-bc90951d0974?w=1200"
    ),

    EventUi(
        "6",
        "Food Festival",
        "Patan",
        "20 Sept",
        "https://images.unsplash.com/photo-1414235077428-338989a2e8c0?w=1200"
    ),

    EventUi(
        "7",
        "Comedy Night",
        "Kathmandu",
        "22 Sept",
        "https://images.unsplash.com/photo-1516280440614-37939bbacd81?w=1200"
    ),

    EventUi(
        "8",
        "Jazz Evening",
        "Pokhara",
        "30 Sept",
        "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=1200"
    )
)