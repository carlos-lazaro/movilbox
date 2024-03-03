package com.example.mobilbox.data.remote.service.dto

import com.squareup.moshi.Json

data class DeleteProductByIdDto(
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
   @Json(name = "isDeleted") var isDeleted: Boolean,
   @Json(name = "deletedOn") var deletedOn: String,
)
