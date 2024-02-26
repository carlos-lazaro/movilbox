package com.example.mobilbox.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mobilbox.ui.navigation.AppNavHost

@Composable
fun MovilboxApp(
        appState : MovilboxAppState = rememberMovilboxAppState(),
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppNavHost(appState)
    }
}
