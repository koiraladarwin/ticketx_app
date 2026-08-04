package com.darwin.ticketx.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
private val LightColors = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,

    primaryContainer = PrimaryLight,
    onPrimaryContainer = TextPrimary,

    secondary = PrimaryDark,
    onSecondary = Color.White,

    background = Background,
    onBackground = TextPrimary,

    surface = Surface,
    onSurface = TextPrimary,

    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = TextSecondary,

    outline = Border,

    error = Error,
    onError = Color.White,

    tertiary = Success,
    onTertiary = Color.White
)


private val DarkColors = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkBackground,

    primaryContainer = PrimaryDark,
    onPrimaryContainer = DarkText,

    secondary = PrimaryLight,
    onSecondary = DarkBackground,

    background = DarkBackground,
    onBackground = DarkText,

    surface = DarkSurface,
    onSurface = DarkText,

    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkSecondary,

    outline = DarkBorder,

    error = Error,
    onError = Color.White,

    tertiary = Success,
    onTertiary = Color.White
)


@Composable
fun TicketXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}