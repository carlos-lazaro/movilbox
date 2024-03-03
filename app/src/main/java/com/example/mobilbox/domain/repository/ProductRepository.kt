package com.example.mobilbox.domain.repository

import com.example.mobilbox.domain.model.Category
import com.example.mobilbox.domain.model.Product
import com.example.mobilbox.domain.model.ProductFilter
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

   suspend fun syncDatabase(): Boolean
   suspend fun getProductById(id: Int): Product?
   fun getProductsByFilter(filter: ProductFilter): Flow<List<Product>>
   fun getProductCategories(): Flow<List<Category>>
   fun getProductBrands(): Flow<List<String>>
   suspend fun deleteProductById(id: Int): Boolean
}
