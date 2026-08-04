package com.darwin.ticketx.feature_ticket.data.dto

data class EventsResponse(
    val `data`: List<DataDto>,
    val message: String,
    val success: Boolean
)