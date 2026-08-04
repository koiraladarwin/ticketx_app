package com.darwin.ticketx.feature_auth.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darwin.ticketx.ui.theme.TicketXTheme

@Composable
fun AuthContent(
    modifier: Modifier = Modifier,
    state: AuthState,
    onLogin: (String,String) -> Unit,
    onSignupClick :()-> Unit
) {
    LaunchedEffect(Unit) {
        println("AuthContent loaded: ${hashCode()}")
    }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "🎫",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Sign in to continue to TicketX",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(40.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            label = { Text("Email") },
            leadingIcon = {
                Icon(
                    Icons.Outlined.Person,
                    null
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text("Password") },
            leadingIcon = {
                Icon(
                    Icons.Outlined.Lock,
                    null
                )
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(12.dp))

        TextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = { }
        ) {
            Text("Forgot Password?")
        }

        Spacer(Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = state !is AuthState.Loading,
            onClick = {
                onLogin(email,password)
            }
        ) {

            if (state is AuthState.Loading) {

                CircularProgressIndicator(
                    modifier = Modifier.size(22.dp),
                    strokeWidth = 2.dp
                )

            } else {

                Text("Sign In")

            }

        }

        Spacer(Modifier.height(20.dp))

        when (val current = state) {

            AuthState.Idle -> {}

            AuthState.Loading -> {}

            is AuthState.Success -> {}

            is AuthState.Error -> {

                Text(
                    text = current.message,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error
                )

            }

        }

        Spacer(Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an account?")

            Spacer(modifier = Modifier.width(4.dp))

            TextButton(
                contentPadding = PaddingValues(0.dp),
                onClick = onSignupClick
            ) {
                Text("Sign Up")
            }
        }

    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AuthPreview() {
    TicketXTheme {
        AuthContent(
            state = AuthState.Idle,
            onLogin = { _, _ -> },
            modifier = TODO(),
            onSignupClick = TODO()
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AuthLoadingPreview() {
    TicketXTheme {
        AuthContent(
            state = AuthState.Loading,
            onLogin = { _, _ -> },
            modifier = TODO(),
            onSignupClick = TODO()
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AuthErrorPreview() {
    TicketXTheme {
        AuthContent(
            state = AuthState.Error("Invalid email or password."),
            onLogin = { _, _ -> },
            modifier = TODO(),
            onSignupClick = TODO()
        )
    }
}