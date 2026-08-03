package com.darwin.ticketx.feature_auth.presentation

import com.darwin.ticketx.feature_auth.domain.model.User

sealed class AuthState {

    data object Idle : AuthState()

    data object Loading : AuthState()

    data class Success(
        val user: User
    ) : AuthState()

    data class Error(
        val message: String
    ) : AuthState()
}