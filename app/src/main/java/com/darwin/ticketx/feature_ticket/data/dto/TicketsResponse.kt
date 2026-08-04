package com.darwin.ticketx.feature_ticket.data.dto

data class TicketsResponse(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)