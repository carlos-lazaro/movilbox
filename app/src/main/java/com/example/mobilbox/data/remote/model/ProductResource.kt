package com.example.mobilbox.data.remote.model

import com.example.mobilbox.data.local.database.model.ProductEntity
import com.squareup.moshi.Json

data class ProductResource(
   @Json(name = "id") var id: Int,
   @Json(name = "title") var title: String,
   @Json(name = "description") var description: String,
   @Json(name = "price") var price: Double,
   @Json(name = "discountPercentage") var discountPercentage: Double,
   @Json(name = "rating") var rating: Double,
   @Json(name = "stock") var stock: Int,
   @Json(name = "brand") var brand: String,
   @Json(name = "category") var category: String,
   @Json(name = "thumbnail") var thumbnail: String,
   @Json(name = "images") var images: List<String> = emptyList(),
)

fun ProductResource.asProductEntity() = ProductEntity(
   id = id,
   title = title,
   description = description,
   price = price,
   discountPercentage = discountPercentage,
   rating = rating,
   stock = stock,
   brand = brand,
   category = category,
   thumbnail = thumbnail,
   images = images,
)
