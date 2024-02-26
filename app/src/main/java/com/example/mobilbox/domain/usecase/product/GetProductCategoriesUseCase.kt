package com.example.mobilbox.domain.usecase.product

import com.example.mobilbox.domain.model.Category
import com.example.mobilbox.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductCategoriesUseCase @Inject constructor(private val productRepository : ProductRepository) {

    operator fun invoke() : Flow<List<Category>> {
        return productRepository.getProductCategories()
    }
}
