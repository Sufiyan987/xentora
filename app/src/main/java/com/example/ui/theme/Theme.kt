package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = EntoraGreenPrimary,
    onPrimary = EntoraOnGreenPrimary,
    primaryContainer = EntoraGreenContainerDark,
    onPrimaryContainer = EntoraOnGreenContainerDark,
    secondary = EntoraSlateLight,
    onSecondary = Color(0xFF090C10),
    secondaryContainer = EntoraSlateContainerDark,
    onSecondaryContainer = Color(0xFFE2E8F0),
    tertiary = EntoraGreenLight,
    onTertiary = Color(0xFF090C10),
    tertiaryContainer = Color(0xFF064E3B),
    onTertiaryContainer = Color(0xFFA7F3D0),
    background = EntoraDarkBackground,
    onBackground = EntoraDarkTextPrimary,
    surface = EntoraDarkSurface,
    onSurface = EntoraDarkTextPrimary,
    surfaceVariant = EntoraDarkSurfaceVariant,
    onSurfaceVariant = EntoraDarkTextSecondary,
    outline = EntoraDarkBorder,
    outlineVariant = Color(0xFF263244),
    error = EntoraError,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = EntoraGreenDark,
    onPrimary = Color.White,
    primaryContainer = EntoraGreenContainerLight,
    onPrimaryContainer = EntoraOnGreenContainerLight,
    secondary = EntoraSlateDark,
    onSecondary = Color.White,
    secondaryContainer = EntoraSlateContainerLight,
    onSecondaryContainer = Color(0xFF0F172A),
    tertiary = EntoraGreenPrimary,
    onTertiary = Color(0xFF041C10),
    tertiaryContainer = Color(0xFFD1FAE5),
    onTertiaryContainer = Color(0xFF065F46),
    background = EntoraLightBackground,
    onBackground = EntoraLightTextPrimary,
    surface = EntoraLightSurface,
    onSurface = EntoraLightTextPrimary,
    surfaceVariant = EntoraLightSurfaceVariant,
    onSurfaceVariant = EntoraLightTextSecondary,
    outline = EntoraLightBorder,
    outlineVariant = Color(0xFFCBD5E1),
    error = EntoraError,
    onError = Color.White
)

enum class ThemeMode {
    SYSTEM,
    LIGHT,
    DARK
}

@Composable
fun EntoraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// Keep backward compatibility
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    EntoraTheme(darkTheme = darkTheme, content = content)
}
