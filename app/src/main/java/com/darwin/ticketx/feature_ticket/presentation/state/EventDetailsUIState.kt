package com.darwin.ticketx.feature_ticket.presentation.state

import com.darwin.ticketx.feature_ticket.domain.model.EventDetailsUi

data class EventDetailsUiState(
    val event: EventDetailsUi? = null,

    // Event loading
    val isLoading: Boolean = false,

    // Purchase loading
    val isPurchasing: Boolean = false,

    // Purchase result
    val purchaseSuccess: Boolean = false,

    // General error
    val error: String? = null,

    // Optional purchase message
    val purchaseMessage: String? = null
)
