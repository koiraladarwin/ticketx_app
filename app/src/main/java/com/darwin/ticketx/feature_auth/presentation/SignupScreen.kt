package com.darwin.ticketx.feature_auth.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.LocalAutofillHighlightColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel


@Composable
fun SignupScreen(
    onLoginClick: () -> Unit,
    onSignupSuccess: () -> Unit,
    viewModel: SignupViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(state.success) {
        if (state.success) {
            onSignupSuccess()
            Toast.makeText(
                context,
                "Account created successfully",
                Toast.LENGTH_SHORT
            ).show()


        }
    }
    Scaffold(
        contentWindowInsets = WindowInsets(0)
    ) { padding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp)
                .padding(padding),
            horizontalAlignment = Alignment.Start
        ) {


            Spacer(
                modifier = Modifier.height(160.dp)
            )


            Text(
                text = "🎟️",
                fontSize = 28.sp
            )


            Spacer(
                modifier = Modifier.height(20.dp)
            )


            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineLarge
            )


            Spacer(
                modifier = Modifier.height(6.dp)
            )


            Text(
                text = "Sign up to start using TicketX",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )


            Spacer(
                modifier = Modifier.height(40.dp)
            )



            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                placeholder = {
                    Text("User Name")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(4.dp),
                singleLine = true
            )


            Spacer(
                modifier = Modifier.height(20.dp)
            )



            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                placeholder = {
                    Text("Email")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(4.dp),
                singleLine = true
            )



            Spacer(
                modifier = Modifier.height(20.dp)
            )



            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                placeholder = {
                    Text("Password")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = null
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(4.dp),
                singleLine = true
            )



            Spacer(
                modifier = Modifier.height(45.dp)
            )



            Button(
                onClick = {

                    viewModel.signup(
                        email = email,
                        username = name,
                        password = password
                    )

                },
                enabled = !state.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(30.dp)
            ) {


                if(state.isLoading){

                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                }else{

                    Text(
                        text = "Sign Up"
                    )

                }

            }



            Spacer(
                modifier = Modifier.height(55.dp)
            )



            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Already have an account?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )


                Spacer(
                    modifier = Modifier.width(4.dp)
                )


                TextButton(
                    onClick = onLoginClick,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.height(40.dp)
                ) {

                    Text(
                        text = "Sign In",
                        style = MaterialTheme.typography.bodyMedium
                    )

                }
            }

        }

    }

}