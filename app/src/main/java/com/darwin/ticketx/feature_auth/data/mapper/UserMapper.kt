package com.darwin.ticketx.feature_auth.data.mapper

import com.darwin.ticketx.feature_auth.data.dto.UserResponse
import com.darwin.ticketx.feature_auth.domain.model.User

fun UserResponse.toDomain(): User {
    return User(
        id = id,
        email = email,
        username = username
    )
}