package com.example.simplenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplenotes.ui.navigation.AppNavHost
import com.example.simplenotes.ui.navigation.RememberNavController
import com.example.simplenotes.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkTheme by viewModel.darkTheme.observeAsState(false)
            AppTheme(darkTheme = darkTheme) {
                com.example.simplenotes.ui.navigation.AppNavHost(RememberNavController())
            }
        }
    }
}