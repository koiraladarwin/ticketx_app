package com.darwin.ticketx.feature_ticket.presentation

data class EventDetailsUiState(
    val event: EventDetailsUi? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
