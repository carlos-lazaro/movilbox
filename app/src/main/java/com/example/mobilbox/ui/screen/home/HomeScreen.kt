package com.example.mobilbox.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobilbox.domain.usecase.product.ProductFilter
import com.example.mobilbox.ui.MovilboxAppState
import com.example.mobilbox.ui.component.ProductCard
import com.example.mobilbox.ui.component.RowFilters
import com.example.mobilbox.ui.component.SearchBarProducts
import com.example.mobilbox.ui.screen.product.detail.navigation.navigateToProductDetail
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeRoute(
        appState : MovilboxAppState,
        modifier : Modifier = Modifier,
        homeViewModel : HomeViewModel = hiltViewModel(),
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(appState, modifier, homeViewModel.uiEvent, uiState) { event ->
        homeViewModel.onEvent(event)
    }
}

@Composable
fun HomeScreen(
        appState : MovilboxAppState,
        modifier : Modifier = Modifier,
        uiEvent : Flow<HomeUiEvent>,
        uiState : HomeState,
        onEvent : (HomeEvent) -> Unit,
) {
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(true) {
        uiEvent.collect { event ->
            when (event) {
                HomeUiEvent.ResetScroll -> {
                }
            }
        }
    }
    LaunchedEffect(uiState.products) {
        lazyGridState.scrollToItem(0)
    }

    Column(modifier) {
        SearchBarProducts() { query ->
            onEvent(HomeEvent.OnChangeFilter(ProductFilter.ByTitle(query)))
        }
        RowFilters(
            selectedFilter = uiState.productFilter,
            onChangeSortType = {
                onEvent(HomeEvent.OnChangeSortType(it))
            },
            categories = uiState.categories.map { it.name },
            brands = uiState.brands,
        ) {
            onEvent(HomeEvent.OnChangeFilter(it))
        }
        LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
        ) {
            items(uiState.products,
                  key = { it.id },
                  itemContent = { item ->
                      ProductCard(product = item, onClick = { product ->
                          appState.navController.navigateToProductDetail(product.id)
                      })
                  })
        }
    }
}


