package com.example.mobilbox.domain.usecase.product

import com.example.mobilbox.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductBrandsUseCase @Inject constructor(private val productRepository: ProductRepository) {

   operator fun invoke(): Flow<List<String>> {
      return productRepository.getProductBrands()
   }
}
