package com.darwin.ticketx.feature_ticket.data.dto

data class EventDetailsResponse(
    val `data`: DataDetailsDto,
    val message: String,
    val success: Boolean
)