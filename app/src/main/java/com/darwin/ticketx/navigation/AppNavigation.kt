package com.darwin.ticketx.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.darwin.ticketx.core.session.SessionManager

@Composable
fun AppNavGraph(
    sessionManager: SessionManager
) {
    val rootNavController = rememberNavController()

    LaunchedEffect(Unit) {
        sessionManager.logout.collect {
            Log.d("Testing","session manager triggered")
            rootNavController.navigate(Graph.Auth) {
                popUpTo(rootNavController.graph.id) {
                    inclusive = true
                }
            }

        }
    }

    NavHost(
        navController = rootNavController,
        startDestination = Graph.Main
    ) {
        authNavGraph(rootNavController)
        mainNavGraph(rootNavController)
    }
}