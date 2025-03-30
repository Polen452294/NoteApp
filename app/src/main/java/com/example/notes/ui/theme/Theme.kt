package com.example.notes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = GrayText,
    secondary = LightGrayText,
    background = BlackBackground,
    surface = DarkGrayBackground,
    onPrimary = BlackBackground,
    onSecondary = BlackBackground,
    onBackground = WhiteText,
    onSurface = GrayText,
)
/*val DarkExtendedPalette = DarkColorPalette.copy(
    surfaceTint = WhiteText // цвет для заголовков
)*/

private val LightColorPalette = lightColorScheme(
    primary = BlackBackground,
    secondary = DarkGrayBackground,
    background = GrayText,
    surface = LightGrayText,
    onPrimary = GrayText,
    onSecondary = GrayText,
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

