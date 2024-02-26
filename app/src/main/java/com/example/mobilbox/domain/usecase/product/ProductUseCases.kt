package com.example.mobilbox.domain.usecase.product

data class ProductUseCases(
        val syncDatabaseUseCase : SyncDatabaseUseCase,
        val getProductsUseCase : GetProductsUseCase,
        val getProductByIdUseCase : GetProductByIdUseCase,
        val getProductCategoriesUseCase : GetProductCategoriesUseCase,
        val getProductBrandsUseCase : GetProductBrandsUseCase,
)
