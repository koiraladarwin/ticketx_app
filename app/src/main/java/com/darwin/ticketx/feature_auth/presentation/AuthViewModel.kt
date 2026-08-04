package com.darwin.ticketx.feature_auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.ticketx.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state = _state.asStateFlow()

    fun login(email: String, password: String) {
        Log.d("Testing","login")
        viewModelScope.launch {
            _state.value = AuthState.Loading

            repository.login("test@example.com", "secret123")
                .onSuccess {
                    repository.getMe()
                        .onSuccess { user ->
                            _state.value = AuthState.Success(user)
                        }
                        .onFailure { e ->
                            _state.value = AuthState.Error(e.message ?: "Failed to get user")
                        }
                }
                .onFailure { e ->
                    _state.value = AuthState.Error(e.message ?: "Login failed")
                }
        }
    }
}