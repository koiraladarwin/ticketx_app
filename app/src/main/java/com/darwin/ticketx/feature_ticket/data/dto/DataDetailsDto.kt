package com.darwin.ticketx.feature_ticket.data.dto

data class DataDetailsDto(
    val banner_url: String,
    val capacity: Int,
    val created_by: String,
    val description: String,
    val event_end_at: String,
    val event_start_at: String,
    val id: String,
    val status: String,
    val ticket_sale_end_at: String,
    val ticket_sale_start_at: String,
    val ticket_types: List<TicketTypeDto>,
    val title: String,
    val venue: String
)