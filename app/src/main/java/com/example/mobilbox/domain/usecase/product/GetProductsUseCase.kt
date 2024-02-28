package com.example.mobilbox.domain.usecase.product

import com.example.mobilbox.domain.model.Product
import com.example.mobilbox.domain.model.ProductFilter
import com.example.mobilbox.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val productRepository : ProductRepository) {

    operator fun invoke(filter : ProductFilter) : Flow<List<Product>> {
        return productRepository.getProductsByFilter(filter)
    }
}
