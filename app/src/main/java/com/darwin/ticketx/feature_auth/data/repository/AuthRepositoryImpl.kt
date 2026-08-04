package com.darwin.ticketx.feature_auth.data.repository


import com.darwin.ticketx.core.local.TokenManager
import com.darwin.ticketx.feature_auth.data.api.AuthApi
import com.darwin.ticketx.feature_auth.data.dto.LoginRequest
import com.darwin.ticketx.feature_auth.data.dto.SignupRequest
import com.darwin.ticketx.feature_auth.data.mapper.toDomain
import com.darwin.ticketx.feature_auth.domain.model.User
import com.darwin.ticketx.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val tokenManager: TokenManager
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {

        return try {
            val response =
                api.login(
                    LoginRequest(
                        email,
                        password
                    )
                )
            tokenManager.saveRefreshToken(
                response.refreshToken
            )
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)

        }

    }

    override suspend fun getMe(): Result<User> {
        return try {
            val response =
                api.me()
            Result.success(
                response.toDomain()
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signup(
        request: SignupRequest
    ): Result<Unit> {

        return try {

            val response = api.signup(request)

            when (response.code()) {

                201 -> Result.success(Unit)

                500 -> Result.failure(
                    Exception("User already exists")
                )

                else -> Result.failure(
                    Exception(response.message())
                )
            }


        } catch (e: Exception) {

            Result.failure(e)

        }

    }
}