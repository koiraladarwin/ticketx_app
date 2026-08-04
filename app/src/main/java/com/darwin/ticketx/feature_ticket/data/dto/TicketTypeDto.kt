package com.darwin.ticketx.feature_ticket.data.dto

data class TicketTypeDto(
    val description: String,
    val event_id: String,
    val id: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val remaining: Int
)