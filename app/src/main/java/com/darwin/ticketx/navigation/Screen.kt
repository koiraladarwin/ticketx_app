package com.darwin.ticketx.navigation

sealed class Screen(
    val route: String,
    val title: String? = null,
    val showTopBar: Boolean = false,
    val showBottomBar: Boolean = false,
    val showBackButton: Boolean = false
) {

    data object Login : Screen("login")

    data object Home : Screen(
        route = "home",
        title = "TicketX",
        showTopBar = true,
        showBottomBar = true
    )

    data object Tickets : Screen(
        route = "tickets",
        title = "Tickets",
        showTopBar = false,
        showBottomBar = true
    )

    data object Profile : Screen(
        route = "profile",
        title = "Profile",
        showTopBar = true,
        showBottomBar = true
    )

    data object EventDetails : Screen(
        route = "event_details/{eventId}",
        title = "Event Details",
        showTopBar = false,
        showBackButton = false
    ) {

        fun createRoute(id: String): String {
            return "event_details/$id"
        }
    }

    data object Settings : Screen(
        route = "settings",
        title = "Settings",
        showTopBar = true,
        showBackButton = true
    )

    data object Main : Screen(
        route = "main",
        title = "TicketX Details",
        showTopBar = true
    )

    companion object {
        private val all by lazy {
            listOf(
                Login,
                Main,
                Home,
                Tickets,
                Profile,
                EventDetails,
                Settings
            )
        }

        fun fromRoute(route: String?): Screen? {
            return all.firstOrNull { it.route == route }
        }
    }
}