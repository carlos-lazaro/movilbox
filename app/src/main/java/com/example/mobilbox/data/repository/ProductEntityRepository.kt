package com.example.mobilbox.data.repository

import com.example.mobilbox.data.local.database.dao.CategoryEntityDao
import com.example.mobilbox.data.local.database.dao.ProductEntityDao
import com.example.mobilbox.data.local.database.model.CategoryEntity
import com.example.mobilbox.data.local.database.model.ProductEntity
import com.example.mobilbox.data.local.database.model.asProduct
import com.example.mobilbox.data.local.database.model.toCategory
import com.example.mobilbox.data.remote.model.asProductEntity
import com.example.mobilbox.data.remote.service.ProductService
import com.example.mobilbox.domain.model.Category
import com.example.mobilbox.domain.model.Product
import com.example.mobilbox.domain.repository.ProductRepository
import com.example.mobilbox.domain.model.ProductFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductEntityRepository
@Inject constructor(
        private var productService : ProductService,
        private var productEntityDao : ProductEntityDao,
        private var categoryEntityDao : CategoryEntityDao,
) :
    ProductRepository {

    override suspend fun syncDatabase() : Boolean {
        try {
            val productsResource = productService.getProductResources()
            productEntityDao.upsertProducts(
                productsResource.products.map { it.asProductEntity() }
            )

            val categoriesResource = productService.getProductResourceCategories()
            categoryEntityDao.upsertCategories(categoriesResource.mapIndexed { index, s ->
                CategoryEntity(
                    id = index,
                    name = s,
                )
            })
        } catch (_ : Exception) {
            return false
        }

        return true
    }

    override suspend fun getProductById(id : Int) : Product? {
        return productEntityDao.getProductById(id)?.asProduct()
    }

    override fun getProductsByFilter(filter : ProductFilter) : Flow<List<Product>> {
        return productEntityDao
            .getProductsByFilter(filter)
            .map { it.map(ProductEntity::asProduct) }
    }

    override fun getProductCategories() : Flow<List<Category>> {
        return categoryEntityDao
            .getCategories()
            .map { it.map { c -> c.toCategory() } }
    }

    override fun getProductBrands() : Flow<List<String>> {
        return productEntityDao.getBrands()
    }

    override suspend fun deleteProductById(id : Int) : Boolean {
        try {
            val productResource = productService.deleteProductById(id)

            productResource?.let {
                if (it.isDeleted) {
                    productEntityDao.deleteProductById(id)
                    return true
                }
            }
        } catch (_ : Exception) {
            return false
        }

        return false
    }
}
