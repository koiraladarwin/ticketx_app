package com.darwin.ticketx.feature_ticket.data.dto

data class Data(
    val event: Event,
    val id: String,
    val purchased_at: String,
    val qr_code: String,
    val status: String,
    val ticket_number: String,
    val ticket_type: TicketType
)