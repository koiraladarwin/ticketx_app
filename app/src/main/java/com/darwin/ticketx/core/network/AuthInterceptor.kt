package com.darwin.ticketx.core.network


import android.util.Log
import com.darwin.ticketx.core.local.TokenManager
import com.darwin.ticketx.core.session.SessionManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager,
    private val sessionManager: SessionManager
) : Interceptor {


    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        val token =
            tokenManager.getAccessToken()

        val request =
            chain.request()
                .newBuilder()


        if (token != null) {
            request.addHeader(
                "Authorization",
                "Bearer $token"
            )
        }

        return chain.proceed(
            request.build()
        )
    }
}