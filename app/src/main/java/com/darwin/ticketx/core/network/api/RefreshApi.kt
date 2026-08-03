package com.darwin.ticketx.core.network

import retrofit2.http.Body
import retrofit2.http.POST


interface RefreshApi {

    @POST("refreshtoken")
    suspend fun refresh(
        @Body request: RefreshRequest
    ): RefreshResponse

}


data class RefreshRequest(
    val token: String
)


data class RefreshResponse(
    val token: String
)