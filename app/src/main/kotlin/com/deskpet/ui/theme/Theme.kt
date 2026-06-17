package com.deskpet.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB3C6),
    secondary = Color(0xFFFFD6E0),
    tertiary = Color(0xFFFFF0F3),
    background = Color(0xFF1A1A2E),
    surface = Color(0xFF252540),
    onPrimary = Color(0xFF3D0020),
    onSecondary = Color(0xFF3D0020),
    onBackground = Color(0xFFE8E8F0),
    onSurface = Color(0xFFE8E8F0),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFE84393),
    secondary = Color(0xFFFF6B9D),
    tertiary = Color(0xFFFFDEE4),
    background = Color(0xFFFFF5F7),
    surface = Color(0xFFFFF5F7),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFF2D2D3A),
    onSurface = Color(0xFF2D2D3A),
)

@Composable
fun DeskPetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
