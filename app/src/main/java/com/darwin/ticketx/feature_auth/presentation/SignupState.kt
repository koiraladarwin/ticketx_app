package com.darwin.ticketx.feature_auth.presentation

data class SignupState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)