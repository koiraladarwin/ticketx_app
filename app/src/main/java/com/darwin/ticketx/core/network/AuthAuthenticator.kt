package com.darwin.ticketx.core.network


import android.util.Log
import com.darwin.ticketx.core.local.TokenManager
import com.darwin.ticketx.core.session.SessionManager
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject


class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val refreshApi: RefreshApi,
    private val sessionManager: SessionManager,
) : Authenticator {


    override fun authenticate(
        route: Route?,
        response: Response
    ): Request? {
        Log.d("Testing", "authenticator triggered 1")
        if (responseCount(response) >= 2) {
            Log.d("Testing", "authenticator triggered 2")
            runBlocking {
                tokenManager.clearTokens()
                sessionManager.logout()
            }
            return null
        }

        val refreshToken =
            runBlocking {
                tokenManager.getRefreshToken()
            }
                ?: run {
                    runBlocking {
                        tokenManager.clearTokens()
                        sessionManager.logout()
                    }
                    return null
                }

        val newToken = try {
            runBlocking {
                var res = refreshApi.refresh(
                    RefreshRequest(
                        token = refreshToken
                    )
                )
                res.token
            }
        } catch (e: Exception) {
            runBlocking {
                tokenManager.clearTokens()
                sessionManager.logout()
            }
            null
        }

        if (newToken == null) {
            runBlocking {
                tokenManager.clearTokens()
                sessionManager.logout()
            }
            return null
        }

        runBlocking {
            tokenManager.saveAccessToken(
                newToken
            )
        }

        return response.request
            .newBuilder()
            .header(
                "Authorization",
                "Bearer $newToken"
            )
            .build()
    }


    private fun responseCount(
        response: Response
    ): Int {

        var count = 1
        var prior = response.priorResponse
        while (prior != null) {
            count++
            prior = prior.priorResponse
        }
        return count
    }

}