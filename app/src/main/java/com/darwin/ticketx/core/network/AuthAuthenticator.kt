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
        Log.d("Testing","Authenticator triggered")
        if (responseCount(response) >= 2) {
            return null
        }

        val refreshToken =
            runBlocking {
                tokenManager.getRefreshToken()
            }
                ?: return null

        val newToken = try {
            runBlocking {
                var res = refreshApi.refresh(
                    RefreshRequest(
                        token = refreshToken
                    )
                )
                Log.d("Testing", res.token)
                res.token
            }
        } catch (e: Exception) {
            Log.d("Testing", e.message.toString())
            null
        }

        if (newToken == null) {
            runBlocking {
                tokenManager.clearTokens()
                sessionManager.logout()
            }
            return null
        }
        Log.d("Testing","Authenticator triggered 2")
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