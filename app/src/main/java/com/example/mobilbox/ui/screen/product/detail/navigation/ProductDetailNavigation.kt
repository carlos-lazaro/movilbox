package com.example.mobilbox.ui.screen.product.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mobilbox.ui.MovilboxAppState
import com.example.mobilbox.ui.screen.product.detail.ProductDetailRoute

const val PRODUCT_DETAIL_ROUTE = "product_detail_route"

fun NavController.navigateToProductDetail(id: Int) =
   navigate("${PRODUCT_DETAIL_ROUTE}/$id") { launchSingleTop = true }

fun NavGraphBuilder.productDetailScreen(appState: MovilboxAppState) {
   composable(route = "$PRODUCT_DETAIL_ROUTE/{id}") { navBackStackEntry ->
      val id = navBackStackEntry.arguments?.getString("id")

      ProductDetailRoute(appState = appState, id = id?.toInt())
   }
}
