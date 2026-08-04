package com.darwin.ticketx.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.*
import com.darwin.ticketx.feature_ticket.presentation.EventDetailsScreen
import com.darwin.ticketx.feature_ticket.presentation.HomeScreen
import com.darwin.ticketx.feature_user.presentation.screens.ProfileScreen
import com.darwin.ticketx.ui.theme.Border

fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {

    navigation(
        route = Graph.Main,
        startDestination = Screen.Main.route
    ) {

        composable(Screen.Main.route) {
            MainRoot()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainRoot() {

    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = remember(backStackEntry) {
        Screen.fromRoute(backStackEntry?.destination?.route)
    }
    Scaffold(
        contentWindowInsets = WindowInsets(0),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            if (currentScreen?.showTopBar == true) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.onBackground
                    ),
                    title = {
                        Text(
                            text = currentScreen.title ?: "",
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    navigationIcon = {
                        if (currentScreen.showBackButton) {
                            IconButton(
                                onClick = { navController.popBackStack() }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    }
                )
            }
        },

        bottomBar = {

            if (currentScreen?.showBottomBar == true) {
                Column {
                    HorizontalDivider(
                        thickness = 0.5.dp,
                        color = Border
                    )

                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.background,
                        tonalElevation = 0.dp
                    ) {
                        listOf(
                            Screen.Home,
                            Screen.Tickets,
                            Screen.Profile
                        ).forEach { screen ->

                            NavigationBarItem(
                                selected = currentScreen.route == screen.route,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(Screen.Home.route) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,

                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,

                                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.10f)
                                ),
                                icon = {
                                    when (screen) {
                                        Screen.Home ->
                                            Icon(Icons.Default.Home, contentDescription = null)

                                        Screen.Tickets ->
                                            Icon(
                                                Icons.Default.ConfirmationNumber,
                                                contentDescription = null
                                            )

                                        Screen.Profile ->
                                            Icon(Icons.Default.Person, contentDescription = null)

                                        else -> {}
                                    }
                                },
                                label = {
                                    Text(screen.title!!)
                                }
                            )
                        }
                    }
                }
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {

            composable(Screen.Home.route) {

                HomeScreen(
                    onEventClick = { event ->
                        navController.navigate(
                            Screen.EventDetails.createRoute(event.id)
                        )
                    }
                )
            }

            composable(Screen.Tickets.route) {
                TicketScreen()
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            composable(
                route = Screen.EventDetails.route
            ) { backStackEntry ->


                val eventId =
                    backStackEntry.arguments?.getString("eventId")


                EventDetailsScreen(
                    eventId = eventId ?: "",
                    onBack = {
                        navController.popBackStack()
                    }
                )

            }

            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}


@Composable
private fun TicketScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Tickets")
    }
}


@Composable
private fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Settings")
    }
}