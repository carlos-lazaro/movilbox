package com.example.mobilbox.ui.screen.product.home

import com.example.mobilbox.domain.model.ProductFilter
import com.example.mobilbox.domain.model.SortType

sealed interface HomeEvent {
   data class OnChangeFilter(val filter: ProductFilter) : HomeEvent
   data class OnChangeSortType(val sortType: SortType) : HomeEvent
   data object OnResetSync : HomeEvent
}
