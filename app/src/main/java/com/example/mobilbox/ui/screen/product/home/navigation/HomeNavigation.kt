package com.example.mobilbox.ui.screen.product.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mobilbox.ui.MovilboxAppState
import com.example.mobilbox.ui.screen.product.home.HomeRoute

const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome() = navigate(HOME_ROUTE)

fun NavGraphBuilder.homeScreen(appState: MovilboxAppState) {
   composable(route = HOME_ROUTE) {
      HomeRoute(appState)
   }
}
