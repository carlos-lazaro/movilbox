package com.example.mobilbox.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.mobilbox.data.local.database.model.ProductEntity
import com.example.mobilbox.domain.model.ProductFilter
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductEntityDao {

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProductById(id : Int) : ProductEntity?

    @Query("SELECT DISTINCT brand FROM products")
    fun getBrands() : Flow<List<String>>

    @Query("SELECT * FROM products")
    fun getProducts() : Flow<List<ProductEntity>>

    @Query(
        "SELECT * FROM products ORDER BY " +
                "CASE WHEN :sortType = 0 THEN price end ASC, " +
                "CASE WHEN :sortType = 1 THEN price end DESC"
    )
    fun getProductsSortedByPrice(sortType : Int) : Flow<List<ProductEntity>>

    @Query(
        "SELECT * FROM products ORDER BY " +
                "CASE WHEN :sortType = 0 THEN discountPercentage end ASC, " +
                "CASE WHEN :sortType = 1 THEN discountPercentage end DESC"
    )
    fun getProductsSortedByDiscountPercentage(sortType : Int) : Flow<List<ProductEntity>>

    @Query(
        "SELECT * FROM products WHERE category = :category ORDER BY " +
                "CASE WHEN :sortType = 0 THEN price end ASC, " +
                "CASE WHEN :sortType = 1 THEN price end DESC"
    )
    fun getProductsByCategoryAndSortByPrice(
            category : String,
            sortType : Int,
    ) : Flow<List<ProductEntity>>

    @Query(
        "SELECT * FROM products ORDER BY " +
                "CASE WHEN :sortType = 0 THEN rating end ASC, " +
                "CASE WHEN :sortType = 1 THEN rating end DESC"
    )
    fun getProductsSortedByRating(sortType : Int) : Flow<List<ProductEntity>>

    @Query(
        "SELECT * FROM products ORDER BY " +
                "CASE WHEN :sortType = 0 THEN stock end ASC, " +
                "CASE WHEN :sortType = 1 THEN stock end DESC"
    )
    fun getProductsSortedByStock(sortType : Int) : Flow<List<ProductEntity>>

    @Query(
        "SELECT * FROM products WHERE brand=:brand ORDER BY " +
                "CASE WHEN :sortType = 0 THEN price end ASC, " +
                "CASE WHEN :sortType = 1 THEN price end DESC"
    )
    fun getProductsByBrandAndSortByPrice(brand : String, sortType : Int) : Flow<List<ProductEntity>>

    @Query(
        "SELECT * FROM products WHERE title LIKE '%'||:title||'%' ORDER BY " +
                "CASE WHEN :sortType = 0 THEN title end ASC, " +
                "CASE WHEN :sortType = 1 THEN title end DESC"
    )
    fun findAndSortProductsByTitle(
            title : String,
            sortType : Int,
    ) : Flow<List<ProductEntity>>

    fun getProductsByFilter(filter : ProductFilter) : Flow<List<ProductEntity>> {
        return when (filter) {
            is ProductFilter.ByPrice -> getProductsSortedByPrice(filter.sortType.value)
            is ProductFilter.ByDiscountPercentage -> getProductsSortedByDiscountPercentage(filter.sortType.value)
            is ProductFilter.ByCategory -> getProductsByCategoryAndSortByPrice(
                filter.category ?: "",
                filter.sortType.value
            )

            is ProductFilter.ByRating -> getProductsSortedByRating(filter.sortType.value)
            is ProductFilter.ByStock -> getProductsSortedByStock(filter.sortType.value)
            is ProductFilter.ByBrand -> getProductsByBrandAndSortByPrice(
                filter.brand ?: "",
                filter.sortType.value
            )

            is ProductFilter.ByTitle -> findAndSortProductsByTitle(
                filter.title,
                filter.sortType.value,
            )
        }
    }

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteProductById(id : Int)

    @Upsert
    suspend fun upsertProduct(productEntity : ProductEntity)

    @Upsert
    suspend fun upsertProducts(productEntities : List<ProductEntity>)
}
