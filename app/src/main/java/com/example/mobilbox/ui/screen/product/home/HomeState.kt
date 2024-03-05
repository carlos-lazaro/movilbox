package com.example.mobilbox.ui.screen.product.home

import com.example.mobilbox.domain.model.Category
import com.example.mobilbox.domain.model.Product
import com.example.mobilbox.domain.model.ProductFilter

data class HomeState(
   var products: List<Product>,
   var categories: List<Category>,
   var brands: List<String>,
   var productFilter: ProductFilter,
   var stateSync: HomeViewModel.ResourceState,
)
