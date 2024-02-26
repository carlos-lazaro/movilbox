package com.example.mobilbox.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Stable
class MovilboxAppState(
        val navController : NavHostController,
        val coroutineScope : CoroutineScope,
)

@Composable
fun rememberMovilboxAppState(
        coroutineScope : CoroutineScope = rememberCoroutineScope(),
        navController : NavHostController = rememberNavController(),
) : MovilboxAppState {
    return remember(
        navController,
        coroutineScope,
    ) {
        MovilboxAppState(
            navController,
            coroutineScope,
        )
    }
}
