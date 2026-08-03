package com.darwin.ticketx.feature_auth.data.api

import com.darwin.ticketx.feature_auth.data.dto.LoginRequest
import com.darwin.ticketx.feature_auth.data.dto.LoginResponse
import com.darwin.ticketx.feature_auth.data.dto.RefreshResponse
import com.darwin.ticketx.feature_auth.data.dto.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface AuthApi {


    @POST("/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse



    @POST("/refreshtoken")
    suspend fun refreshToken(
        @Body body: Map<String,String>
    ): RefreshResponse



    @GET("/me")
    suspend fun me(): UserResponse

}