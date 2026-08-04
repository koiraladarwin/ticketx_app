package com.darwin.ticketx.feature_ticket.data.api


import com.darwin.ticketx.feature_ticket.data.dto.EventDetailsResponse
import com.darwin.ticketx.feature_ticket.data.dto.EventsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EventApi {

    @GET("events")
    suspend fun getEvents(): EventsResponse


    @GET("eventdetails/{id}")
    suspend fun getEventDetails(
        @Path("id") id: String
    ): EventDetailsResponse
}