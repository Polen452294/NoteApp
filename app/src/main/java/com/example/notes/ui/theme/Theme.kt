package com.example.notes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = WhiteText,
    secondary = LightGrayText,
    background = BlackBackground,
    surface = DarkGrayBackground,
    onPrimary = BlackBackground,
    onSecondary = BlackBackground,
    onBackground = WhiteText,
    onSurface = WhiteText
)

private val LightColorPalette = lightColorScheme(
    primary = BlackBackground,
    secondary = DarkGrayBackground,
    background = WhiteText,
    surface = LightGrayText,
    onPrimary = WhiteText,
    onSecondary = WhiteText,
    onBackground = BlackBackground,
    onSurface = BlackBackground
)

@Composable
fun NotesTheme(darkTheme: Boolean = true, content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
