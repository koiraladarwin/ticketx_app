package com.darwin.ticketx.feature_ticket.domain.repository

import com.darwin.ticketx.feature_ticket.domain.model.EventUi
import com.darwin.ticketx.feature_ticket.presentation.EventDetailsUi

interface EventRepository {

    suspend fun getEvents(): Result<List<EventUi>>

    suspend fun getEventDetails(
        id: String
    ): Result<EventDetailsUi>

}