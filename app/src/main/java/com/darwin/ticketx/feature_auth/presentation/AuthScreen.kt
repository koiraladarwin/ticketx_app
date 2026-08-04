package com.darwin.ticketx.feature_auth.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.darwin.ticketx.feature_auth.domain.model.User

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    onLoginSuccess: (User) -> Unit,
    onSignupClick : ()->Unit,
    onForgotPasswordClick : ()->Unit,
    vm: AuthViewModel = hiltViewModel()
) {

    val state by vm.state.collectAsState()

    LaunchedEffect(state) {
        if (state is AuthState.Success) {
            onLoginSuccess((state as AuthState.Success).user)
        }
    }

    AuthContent(
        modifier = modifier,
        state = state,
        onLogin = vm::login,
        onSignupClick = onSignupClick
    )
}