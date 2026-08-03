package com.darwin.ticketx.feature_auth.data.dto

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("refresh_token")
    val refreshToken:String
)