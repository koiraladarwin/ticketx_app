package com.darwin.ticketx.feature_ticket.domain.repository

import com.darwin.ticketx.feature_ticket.domain.model.EventModel
import com.darwin.ticketx.feature_ticket.domain.model.EventDetailsUi

interface EventRepository {

    suspend fun getEvents(): Result<List<EventModel>>

    suspend fun getEventDetails(
        id: String
    ): Result<EventDetailsUi>

    suspend fun purchaseTicket(
        ticketTypeId: String
    ): Result<Boolean>

}