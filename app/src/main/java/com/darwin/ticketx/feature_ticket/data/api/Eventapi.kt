package com.darwin.ticketx.feature_ticket.data.api


import com.darwin.ticketx.feature_ticket.data.dto.EventDetailsResponse
import com.darwin.ticketx.feature_ticket.data.dto.EventsResponse
import com.darwin.ticketx.feature_ticket.data.dto.PurchaseRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventApi {

    @GET("events")
    suspend fun getEvents(): EventsResponse

    @GET("eventdetails/{id}")
    suspend fun getEventDetails(
        @Path("id") id: String
    ): EventDetailsResponse


    @POST("tickets/purchase")
    suspend fun purchaseTicket(
        @Body request: PurchaseRequest
    ): Response<Unit>
}