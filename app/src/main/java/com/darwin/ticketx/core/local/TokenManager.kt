package com.darwin.ticketx.core.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext
    private val context: Context
) {


    private val prefs =
        context.getSharedPreferences(
            "auth_storage",
            Context.MODE_PRIVATE
        )


    fun saveAccessToken(token: String) {

        prefs.edit()
            .putString(
                "access_token",
                token
            )
            .apply()
    }


    fun getAccessToken(): String? {

        return prefs.getString(
            "access_token",
            null
        )
    }


    fun saveRefreshToken(token: String) {

        prefs.edit()
            .putString(
                "refresh_token",
                token
            )
            .apply()
    }


    fun getRefreshToken(): String? {

        return prefs.getString(
            "refresh_token",
            null
        )
    }


    fun clearTokens() {

        prefs.edit()
            .clear()
            .apply()
    }
}