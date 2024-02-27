package com.example.mobilbox.data.remote.service

import com.example.mobilbox.data.remote.service.dto.DeleteProductByIdDto
import com.example.mobilbox.data.remote.service.dto.GetProductResourcesDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products?limit=100")
    suspend fun getProductResources() : GetProductResourcesDto

    @GET("products/categories")
    suspend fun getProductResourceCategories() : List<String>

    @DELETE("products/{id}")
    suspend fun deleteProductById(@Path("id") id : Int) : DeleteProductByIdDto?
}
