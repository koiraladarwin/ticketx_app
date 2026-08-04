package com.darwin.ticketx.feature_ticket.domain.model

data class EventModel(
    val id: String,
    val title: String,
    val venue: String,
    val date: String,
    val imageUrl: String
)