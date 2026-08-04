package com.darwin.ticketx.feature_ticket.data.dto

data class DataDto(
    val banner_url: String,
    val capacity: Int,
    val description: String,
    val event_end_at: String,
    val event_start_at: String,
    val id: String,
    val status: String,
    val ticket_sale_end_at: String,
    val ticket_sale_start_at: String,
    val title: String,
    val venue: String
)