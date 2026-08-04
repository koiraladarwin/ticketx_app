package com.darwin.ticketx.feature_ticket.presentation

import com.darwin.ticketx.feature_ticket.domain.model.EventUi


data class HomeUiState(
    val events: List<EventUi> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)