package com.example.mobilbox.ui.screen.product.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobilbox.domain.model.Product
import com.example.mobilbox.domain.model.ProductFilter
import com.example.mobilbox.ui.MovilboxAppState
import com.example.mobilbox.ui.screen.product.detail.navigation.navigateToProductDetail
import com.example.mobilbox.ui.screen.product.home.component.ProductCard
import com.example.mobilbox.ui.screen.product.home.component.ProductFiltersRow
import com.example.mobilbox.ui.screen.product.home.component.SearchBarForProducts
import com.example.mobilbox.ui.screen.product.home.component.SyncDatabaseStateIndicator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
   appState: MovilboxAppState,
   modifier: Modifier = Modifier,
   homeViewModel: HomeViewModel = hiltViewModel(),
) {
   val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

   HomeScreen(appState, modifier, homeViewModel.uiEvent, uiState) { event ->
      homeViewModel.onEvent(event)
   }
}

@Composable
fun HomeScreen(
   appState: MovilboxAppState,
   modifier: Modifier = Modifier,
   uiEvent: Flow<HomeUiEvent>,
   uiState: HomeState,
   onEvent: (HomeEvent) -> Unit,
) {
   val coroutineScope = appState.coroutineScope
   val lazyGridState = rememberLazyGridState()
   var shouldResetScroll by remember {
      mutableStateOf(false)
   }
   val showButton by remember {
      derivedStateOf {
         lazyGridState.firstVisibleItemIndex > 0
      }
   }

   suspend fun resetScroll() {
      lazyGridState.animateScrollToItem(0)
   }

   LaunchedEffect(Unit) {
      uiEvent.collect { event ->
         when (event) {
            HomeUiEvent.ResetScroll -> {
               shouldResetScroll = true
            }
         }
      }
   }

   LaunchedEffect(uiState.products, shouldResetScroll) {
      if (shouldResetScroll) {
         resetScroll()
         shouldResetScroll = false
      }
   }

   Scaffold(
      floatingActionButton = {
         if (showButton) {
            FloatingActionButton(onClick = {
               coroutineScope.launch {
                  resetScroll()
               }
            }) {
               Icon(
                  Icons.Filled.KeyboardArrowUp,
                  contentDescription = null,
                  tint = MaterialTheme.colorScheme.primary,
               )
            }
         }
      },
      modifier = modifier
   ) { paddingValues ->
      Column(
         Modifier.padding(paddingValues)
      ) {
         SearchBarForProducts { query ->
            onEvent(HomeEvent.OnChangeFilter(ProductFilter.ByTitle(query)))
         }
         ProductFiltersRow(
            selectedFilter = uiState.productFilter,
            onChangeSortType = {
               onEvent(HomeEvent.OnChangeSortType(it))
            },
            categories = uiState.categories.map { it.name },
            brands = uiState.brands,
         ) {
            onEvent(HomeEvent.OnChangeFilter(it))
         }
         SyncDatabaseStateIndicator(uiState.stateSync) {
            onEvent(HomeEvent.OnResetSync)
         }
         LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f)
         ) {
            items(uiState.products, key = Product::id) { item ->
               ProductCard(product = item, onClick = { product ->
                  appState.navController.navigateToProductDetail(product.id)
               })
            }
         }
      }
   }
}
