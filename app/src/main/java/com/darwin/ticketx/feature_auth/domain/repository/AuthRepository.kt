package com.darwin.ticketx.feature_auth.domain.repository


import com.darwin.ticketx.feature_auth.domain.model.User


interface AuthRepository {


    suspend fun login(
        email:String,
        password:String
    ):Result<Unit>


    suspend fun getMe():Result<User>

}