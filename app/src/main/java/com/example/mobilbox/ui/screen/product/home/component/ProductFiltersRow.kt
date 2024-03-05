package com.example.mobilbox.ui.screen.product.home.component

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobilbox.R
import com.example.mobilbox.domain.model.ProductFilter
import com.example.mobilbox.domain.model.SortType

internal data class ModalConfig(
   @StringRes val title: Int,
   val items: List<String>,
   val onClick: (String) -> Unit,
)

@Composable
fun ProductFiltersRow(
   modifier: Modifier = Modifier,
   categories: List<String>,
   brands: List<String>,
   filters: List<ProductFilter> = ProductFilter.filterElements,
   selectedFilter: ProductFilter,
   onChangeSortType: (SortType) -> Unit,
   onChangeFilter: (ProductFilter) -> Unit,
) {
   var showBottomSheet by remember { mutableStateOf(false) }
   var modalConfig by remember { mutableStateOf<ModalConfig?>(null) }

   fun isSelected(filter: ProductFilter): Boolean {
      return filter.id == selectedFilter.id
   }

   fun invertFilter(sortType: SortType): SortType {
      return if (sortType == SortType.DESC) {
         SortType.ASC
      } else {
         SortType.DESC
      }
   }

   Row {
      ElevatedAssistChip(
         onClick = {
            onChangeSortType(invertFilter(selectedFilter.sortType))
         },
         label = { Text(selectedFilter.sortType.name) },
         trailingIcon = {
            Icon(
               selectedFilter.sortType.icon,
               contentDescription = null,
               Modifier.size(AssistChipDefaults.IconSize)
            )
         },
         modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_bx2)),
      )
      Row(
         modifier = modifier
            .horizontalScroll(rememberScrollState())
      ) {
         filters.forEach { filter ->
            ElevatedAssistChip(
               onClick = {
                  when (filter) {
                     is ProductFilter.ByBrand -> {
                        showBottomSheet = true
                        modalConfig = ModalConfig(
                           items = brands,
                           title = R.string.modal_sheet_brands_title,
                           onClick = { brand ->
                              onChangeFilter(ProductFilter.ByBrand(brand = brand))
                           }
                        )
                     }

                     is ProductFilter.ByCategory -> {
                        showBottomSheet = true
                        modalConfig = ModalConfig(
                           items = categories,
                           title = R.string.modal_sheet_categories_title,
                           onClick = { category ->
                              onChangeFilter(ProductFilter.ByCategory(category = category))
                           }
                        )
                     }

                     is ProductFilter.ByDiscountPercentage -> onChangeFilter(
                        filter.copy(sortType = selectedFilter.sortType)
                     )

                     is ProductFilter.ByPrice -> onChangeFilter(
                        filter.copy(sortType = selectedFilter.sortType)
                     )

                     is ProductFilter.ByRating -> onChangeFilter(
                        filter.copy(sortType = selectedFilter.sortType)
                     )

                     is ProductFilter.ByStock -> onChangeFilter(
                        filter.copy(sortType = selectedFilter.sortType)
                     )

                     is ProductFilter.ByTitle -> onChangeFilter(
                        filter.copy(sortType = selectedFilter.sortType)
                     )

                  }
               },
               border = if (isSelected(filter)) BorderStroke(
                  1.dp,
                  MaterialTheme.colorScheme.primary
               ) else null,
               label = { Text(stringResource(filter.idResource)) },
               modifier = Modifier
                  .padding(horizontal = dimensionResource(R.dimen.padding_bx2))
            )
         }
      }
   }

   if (showBottomSheet) {
      modalConfig?.let { config ->
         ModalSelectOneBottomSheet(
            config.title,
            elements = config.items,
            onItemSelected = { item ->
               showBottomSheet = false
               config.onClick(item)
            },
            onDismiss = {
               showBottomSheet = false
            }
         )
      }
   }
}

@Preview
@Composable
fun ProductFiltersRowPreview() {
   Column {
      ProductFiltersRow(
         selectedFilter = ProductFilter.ByBrand(),
         categories = emptyList(),
         brands = emptyList(),
         onChangeSortType = {}) {}
   }
}
