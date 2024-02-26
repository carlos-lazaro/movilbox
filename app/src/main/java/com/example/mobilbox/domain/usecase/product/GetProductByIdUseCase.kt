package com.example.mobilbox.domain.usecase.product

import com.example.mobilbox.domain.model.Product
import com.example.mobilbox.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(private val productRepository : ProductRepository) {

    suspend operator fun invoke(id : Int) : Product? {
        return productRepository.getProductById(id)
    }
}
