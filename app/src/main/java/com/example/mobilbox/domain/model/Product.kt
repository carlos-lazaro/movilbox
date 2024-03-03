package com.example.mobilbox.domain.model

import java.math.BigDecimal
import java.math.RoundingMode

data class Product(
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
) {

   fun getPriceWithDiscount(): Double {
      val priceDiscount = price * ((100 - discountPercentage) / 100)
      return BigDecimal(priceDiscount).setScale(3, RoundingMode.HALF_EVEN).toDouble()
   }

   companion object {

      fun getFake(): Product {
         return Product(
            id = 1000,
            title = "Example Product",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            price = 99.99,
            discountPercentage = 10.0,
            rating = 4.5,
            stock = 100,
            brand = "Brand",
            category = "Category",
            thumbnail = "",
            images = listOf()
         )
      }
   }
}
