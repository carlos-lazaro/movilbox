package com.example.mobilbox.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.example.mobilbox.ui.MovilboxAppState
import com.example.mobilbox.ui.screen.home.navigation.HOME_ROUTE
import com.example.mobilbox.ui.screen.home.navigation.homeScreen
import com.example.mobilbox.ui.screen.product.detail.navigation.productDetailScreen

@Composable
fun AppNavHost(appState: MovilboxAppState) {
   NavHost(navController = appState.navController, startDestination = HOME_ROUTE) {
      homeScreen(appState)
      productDetailScreen(appState)
   }
}
