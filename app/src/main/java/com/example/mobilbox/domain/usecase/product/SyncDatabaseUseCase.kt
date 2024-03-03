package com.example.mobilbox.domain.usecase.product

import com.example.mobilbox.domain.repository.ProductRepository
import javax.inject.Inject

class SyncDatabaseUseCase @Inject constructor(private val productRepository: ProductRepository) {

   suspend operator fun invoke(): Boolean {
      return productRepository.syncDatabase()
   }
}
