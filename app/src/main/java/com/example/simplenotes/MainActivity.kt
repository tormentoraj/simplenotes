package com.example.simplenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplenotes.data.preferences.ThemePreferences
import com.example.simplenotes.ui.navigation.AppNavHost
import com.example.simplenotes.ui.navigation.RememberNavController
import com.example.simplenotes.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Load theme preference
        CoroutineScope(Dispatchers.IO).launch {
            ThemePreferences.isDarkMode(this@MainActivity)
                .collect { isDark ->
                    viewModel.setDarkTheme(isDark)
                }
        }
        setContent {
            val darkTheme by viewModel.darkTheme.observeAsState(false)
            AppTheme(darkTheme = darkTheme) {
                com.example.simplenotes.ui.navigation.AppNavHost(RememberNavController())
            }
        }
    }
}