package com.example.mobilbox.ui.screen.product.detail

import com.example.mobilbox.domain.model.Product

data class ProductDetailState(
        var product : Product? = null,
        var stateDelete : ProductDetailViewModel.ResourceState? = null,
)
