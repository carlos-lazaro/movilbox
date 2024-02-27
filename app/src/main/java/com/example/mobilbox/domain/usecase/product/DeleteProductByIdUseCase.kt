package com.example.mobilbox.domain.usecase.product

import com.example.mobilbox.domain.repository.ProductRepository
import javax.inject.Inject

class DeleteProductByIdUseCase @Inject constructor(private val productRepository : ProductRepository) {

    suspend operator fun invoke(id : Int) : Boolean {
        return productRepository.deleteProductById(id)
    }
}
