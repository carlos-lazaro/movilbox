package com.example.mobilbox.data.remote.service

import com.example.mobilbox.data.remote.service.dto.GetProductResources
import retrofit2.http.GET

interface ProductService {

    @GET("products?limit=100")
    suspend fun getProductResources() : GetProductResources

    @GET("products/categories")
    suspend fun getProductResourceCategories() : List<String>
}
