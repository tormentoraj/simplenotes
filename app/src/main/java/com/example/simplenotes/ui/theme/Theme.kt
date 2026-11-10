package com.example.simplenotes.ui.theme

import android.app.Application
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.example.simplenotes.data.preferences.ThemePreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1B1B1F),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE8DEF8),
    onPrimaryContainer = Color(0xFF1D192B),
    secondary = Color(0xFF49454F),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE8DEF8),
    onSecondaryContainer = Color(0xFF1D192B),
    tertiary = Color(0xFF6C5D7B),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFF3EDF7),
    onTertiaryContainer = Color(0xFF23192B),
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    surface = Color(0xFFFCFCFC),
    onSurface = Color(0xFF1C1B1F),
    surfaceContainerHighest = Color(0xFFE6E0E9),
    onSurfaceVariant = Color(0xFF49454F),
    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC4D0),
    background = Color(0xFFFCFCFC),
    onBackground = Color(0xFF1C1B1F)
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFD0BCFF),
    onPrimary = Color(0xFF381E72),
    primaryContainer = Color(0xFF4F378B),
    onPrimaryContainer = Color(0xFFEADDFF),
    secondary = Color(0xFFCCC2DC),
    onSecondary = Color(0xFF332D41),
    secondaryContainer = Color(0xFF4A4458),
    onSecondaryContainer = Color(0xFFE8DEF8),
    tertiary = Color(0xFFEFB8F8),
    onTertiary = Color(0xFF49254F),
    tertiaryContainer = Color(0xFF633B68),
    onTertiaryContainer = Color(0xFFF3EDF7),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
    surfaceContainerHighest = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0),
    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454F),
    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5)
)

@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

object Typography {
    val displayLarge = androidx.compose.material3.Typography.default.displayLarge
    val displayMedium = androidx.compose.material3.Typography.default.displayMedium
    val displaySmall = androidx.compose.material3.Typography.default.displaySmall
    val headlineLarge = androidx.compose.material3.Typography.default.headlineLarge
    val headlineMedium = androidx.compose.material3.Typography.default.headlineMedium
    val headlineSmall = androidx.compose.material3.Typography.default.headlineSmall
    val titleLarge = androidx.compose.material3.Typography.default.titleLarge
    val titleMedium = androidx.compose.material3.Typography.default.titleMedium
    val titleSmall = androidx.compose.material3.Typography.default.titleSmall
    val bodyLarge = androidx.compose.material3.Typography.default.bodyLarge
    val bodyMedium = androidx.compose.material3.Typography.default.bodyMedium
    val bodySmall = androidx.compose.material3.Typography.default.bodySmall
    val labelLarge = androidx.compose.material3.Typography.default.labelLarge
    val labelMedium = androidx.compose.material3.Typography.default.labelMedium
    val labelSmall = androidx.compose.material3.Typography.default.labelSmall
}

fun saveThemePreference(application: Application, isDark: Boolean) {
    CoroutineScope(Dispatchers.IO).launch {
        ThemePreferences.setDarkMode(application, isDark)
    }
}