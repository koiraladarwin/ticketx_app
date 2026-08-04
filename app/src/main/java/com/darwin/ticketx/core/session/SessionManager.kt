package com.darwin.ticketx.core.session

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val _logout = MutableSharedFlow<Unit>()

    val logout = _logout.asSharedFlow()

    suspend fun logout() {
        _logout.emit(Unit)
    }
}