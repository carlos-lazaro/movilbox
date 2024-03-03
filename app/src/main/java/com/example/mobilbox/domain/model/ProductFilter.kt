package com.example.mobilbox.domain.model

import android.os.Parcelable
import androidx.annotation.StringRes
import com.example.mobilbox.R
import kotlinx.parcelize.Parcelize

sealed class ProductFilter(
   open var sortType: SortType = SortType.DESC,
   @StringRes val idResource: Int,
   val id: Int,
) : Parcelable {

   @Parcelize
   data class ByPrice(override var sortType: SortType = SortType.DESC) :
      ProductFilter(sortType, R.string.filter_name_price, 1)

   @Parcelize
   data class ByDiscountPercentage(override var sortType: SortType = SortType.DESC) :
      ProductFilter(sortType, R.string.filter_name_discount_percentage, 2)

   @Parcelize
   data class ByCategory(
      var category: String? = null,
      override var sortType: SortType = SortType.DESC,
   ) :
      ProductFilter(sortType, R.string.filter_name_category, 3)

   @Parcelize
   data class ByRating(override var sortType: SortType = SortType.DESC) :
      ProductFilter(sortType, R.string.filter_name_rating, 4)

   @Parcelize
   data class ByStock(override var sortType: SortType = SortType.DESC) :
      ProductFilter(sortType, R.string.filter_name_stock, 5)

   @Parcelize
   data class ByBrand(
      var brand: String? = null,
      override var sortType: SortType = SortType.DESC,
   ) :
      ProductFilter(sortType, R.string.filter_name_brand, 6)

   @Parcelize
   data class ByTitle(
      var title: String,
      override var sortType: SortType = SortType.DESC,
   ) :
      ProductFilter(sortType, R.string.filter_name_title, 7)

   companion object {

      val filterElements = listOf(
         ByRating(),
         ByCategory(),
         ByStock(),
         ByBrand(),
         ByPrice(),
         ByDiscountPercentage(),
      )
   }
}
