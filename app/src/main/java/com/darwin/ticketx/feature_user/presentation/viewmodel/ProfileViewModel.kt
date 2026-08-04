package com.darwin.ticketx.feature_user.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.ticketx.core.local.TokenManager
import com.darwin.ticketx.core.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val tokenManager: TokenManager,
    val sessionManager: SessionManager
) : ViewModel() {
    fun logout(){
        viewModelScope.launch {
            tokenManager.clearTokens()
            sessionManager.logout()
        }
    }
}