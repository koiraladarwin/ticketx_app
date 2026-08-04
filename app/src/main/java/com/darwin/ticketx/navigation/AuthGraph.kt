package com.darwin.ticketx.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.darwin.ticketx.feature_auth.presentation.AuthScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavController

) {
    navigation(
        startDestination = Screen.Login.route,
        route = Graph.Auth
    ) {
        composable(
            Screen.Login.route
        ) {
            AuthScreen(
                onLoginSuccess = {
                    navController.navigate(Graph.Main) {
                        popUpTo(Graph.Auth) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
