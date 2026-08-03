package com.darwin.ticketx.feature_auth.data.dto

data class SignupRequest(
    val email:String,
    val username:String,
    val password:String
)