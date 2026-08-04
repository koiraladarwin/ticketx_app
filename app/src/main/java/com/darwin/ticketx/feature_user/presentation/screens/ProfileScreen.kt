package com.darwin.ticketx.feature_user.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.darwin.ticketx.feature_user.presentation.components.ProfileHeader
import com.darwin.ticketx.feature_user.presentation.components.ProfileItem
import com.darwin.ticketx.feature_user.presentation.components.ProfileSection
import com.darwin.ticketx.feature_user.presentation.components.ProfileSwitchItem
import com.darwin.ticketx.feature_user.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {

    var darkMode by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 20.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        item {

            ProfileHeader(
                name = "Darwin Koirala",
                email = "darwin@email.com"
            )

        }

        item {

            ProfileSection("Account") {

                ProfileItem(
                    icon = Icons.Default.ConfirmationNumber,
                    title = "My Tickets"
                ) {}

                HorizontalDivider()

                ProfileItem(
                    icon = Icons.Default.History,
                    title = "Purchase History"
                ) {}

                HorizontalDivider()

                ProfileItem(
                    icon = Icons.Default.FavoriteBorder,
                    title = "Favorites"
                ) {}

            }

        }

        item {

            ProfileSection("Preferences") {

                ProfileItem(
                    icon = Icons.Default.Notifications,
                    title = "Notifications"
                ) {}

                HorizontalDivider()

                ProfileSwitchItem(
                    icon = Icons.Default.DarkMode,
                    title = "Dark Mode",
                    checked = darkMode,
                    onCheckedChange = {
                        darkMode = it
                    }
                )

                HorizontalDivider()

                ProfileItem(
                    icon = Icons.Default.Language,
                    title = "Language",
                    value = "English"
                ) {}

            }

        }

        item {

            ProfileSection("Support") {

                ProfileItem(
                    icon = Icons.Default.HelpOutline,
                    title = "Help & Support"
                ) {}

                HorizontalDivider()

                ProfileItem(
                    icon = Icons.Default.Info,
                    title = "About TicketX"
                ) {}

            }

        }

        item {

            ProfileSection("Security") {

                ProfileItem(
                    icon = Icons.Default.Lock,
                    title = "Change Password"
                ) {}

                HorizontalDivider()

                ProfileItem(
                    icon = Icons.Default.Logout,
                    title = "Sign Out",
                    destructive = true
                ) {
                    viewModel.logout()
                }

            }

        }

        item {
            Spacer(
                Modifier.navigationBarsPadding()
            )
        }

    }

}