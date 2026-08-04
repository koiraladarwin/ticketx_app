package com.darwin.ticketx.feature_ticket.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.darwin.ticketx.feature_ticket.domain.model.EventModel
import com.darwin.ticketx.feature_ticket.presentation.components.FeaturedCarousel
import com.darwin.ticketx.feature_ticket.presentation.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    onEventClick: (EventModel) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {


    val state by viewModel.state.collectAsState()


    when {


        state.isLoading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator()

            }

        }


        state.error != null && state.events.isEmpty() -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = state.error ?: "Something went wrong",
                    color = MaterialTheme.colorScheme.error
                )

            }

        }


        state.events.isEmpty() -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "No events available"
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
                modifier = Modifier.fillMaxSize()
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
                            events = state.events.take(6),
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
                        items = state.events,
                        key = {
                            it.id
                        }
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
        }
    }
}