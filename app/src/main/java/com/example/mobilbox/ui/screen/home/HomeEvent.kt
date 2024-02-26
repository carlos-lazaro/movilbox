package com.example.mobilbox.ui.screen.home

import com.example.mobilbox.domain.usecase.product.ProductFilter
import com.example.mobilbox.domain.usecase.product.SortType

sealed interface HomeEvent {
    data class OnChangeFilter(val filter : ProductFilter) : HomeEvent
    data class OnChangeSortType(val sortType : SortType) : HomeEvent
}
