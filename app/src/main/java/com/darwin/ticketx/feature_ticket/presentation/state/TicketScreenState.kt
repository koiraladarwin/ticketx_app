package com.darwin.ticketx.feature_ticket.presentation.state

import com.darwin.ticketx.feature_ticket.domain.model.TicketModel

data class TicketScreenState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val tickets: List<TicketModel> = emptyList(),
    val error: String? = null
)