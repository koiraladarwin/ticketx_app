package com.darwin.ticketx.feature_ticket.domain.model

data class TicketModel(
    val id: String,
    val image: String,
    val eventTitle: String,
    val ticketType: String,
    val venue: String,
    val eventDate: String,
    val price: String
)