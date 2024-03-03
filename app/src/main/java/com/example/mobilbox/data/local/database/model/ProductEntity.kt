package com.example.mobilbox.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mobilbox.domain.model.Product

@Entity(tableName = "products")
data class ProductEntity(
   @PrimaryKey
   val id: Int,
   val title: String,
   val description: String,
   val price: Double,
   val discountPercentage: Double,
   val rating: Double,
   val stock: Int,
   val brand: String,
   val category: String,
   val thumbnail: String,
   val images: List<String>,
)

fun ProductEntity.asProduct() = Product(
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
