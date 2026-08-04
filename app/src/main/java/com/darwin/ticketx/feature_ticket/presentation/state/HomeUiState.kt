package com.darwin.ticketx.feature_ticket.presentation.state

import com.darwin.ticketx.feature_ticket.domain.model.EventModel

data class HomeUiState(
    val events: List<EventModel> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)