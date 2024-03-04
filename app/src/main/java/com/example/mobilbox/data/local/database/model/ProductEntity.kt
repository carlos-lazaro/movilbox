package com.example.mobilbox.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mobilbox.domain.model.Product

@Entity(tableName = "products")
data class ProductEntity(
   @PrimaryKey
   @ColumnInfo("id")
   val id: Int,
   @ColumnInfo("title")
   val title: String,
   @ColumnInfo("description")
   val description: String,
   @ColumnInfo("price")
   val price: Double,
   @ColumnInfo("discountPercentage")
   val discountPercentage: Double,
   @ColumnInfo("rating")
   val rating: Double,
   @ColumnInfo("stock")
   val stock: Int,
   @ColumnInfo("brand")
   val brand: String,
   @ColumnInfo("category")
   val category: String,
   @ColumnInfo("thumbnail")
   val thumbnail: String,
   @ColumnInfo("images")
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
