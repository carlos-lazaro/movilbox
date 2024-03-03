package com.example.mobilbox.domain.usecase

import com.example.mobilbox.domain.usecase.product.DeleteProductByIdUseCase
import com.example.mobilbox.domain.usecase.product.GetProductBrandsUseCase
import com.example.mobilbox.domain.usecase.product.GetProductByIdUseCase
import com.example.mobilbox.domain.usecase.product.GetProductCategoriesUseCase
import com.example.mobilbox.domain.usecase.product.GetProductsUseCase
import com.example.mobilbox.domain.usecase.product.SyncDatabaseUseCase

data class ProductUseCases(
   val syncDatabaseUseCase: SyncDatabaseUseCase,
   val getProductsUseCase: GetProductsUseCase,
   val getProductByIdUseCase: GetProductByIdUseCase,
   val getProductCategoriesUseCase: GetProductCategoriesUseCase,
   val getProductBrandsUseCase: GetProductBrandsUseCase,
   val deleteProductByIdUseCase: DeleteProductByIdUseCase,
)
