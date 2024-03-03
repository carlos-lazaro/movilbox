package com.example.mobilbox.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilbox.domain.model.Category
import com.example.mobilbox.domain.model.ProductFilter
import com.example.mobilbox.domain.usecase.ProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
   private val productUseCases: ProductUseCases,
) : ViewModel() {

   private val _uiEvent = Channel<HomeUiEvent>()
   val uiEvent = _uiEvent.receiveAsFlow()

   private val stateSync = MutableStateFlow<ResourceState>(ResourceState.Loading)

   private val currentFilter = MutableStateFlow<ProductFilter>(ProductFilter.ByRating())

   private val categories = productUseCases.getProductCategoriesUseCase()

   private val brands = productUseCases.getProductBrandsUseCase()

   @OptIn(ExperimentalCoroutinesApi::class)
   val uiState: StateFlow<HomeState> = combine(
      categories,
      brands,
      currentFilter,
      stateSync,
   ) { categories, brands, filter, stateSync ->
      HomeStateData(categories, brands, filter, stateSync)
   }.flatMapLatest { (categories, brands, filter, stateSync) ->
      productUseCases
         .getProductsUseCase(filter)
         .map { products ->
            HomeState(
               products = products,
               productFilter = filter,
               categories = categories,
               brands = brands,
               stateSync = stateSync,
            )
         }
         .onEach {
            sendUiEvent(HomeUiEvent.ResetScroll)
         }
   }.stateIn(
      viewModelScope,
      SharingStarted.WhileSubscribed(5_000),
      HomeState(emptyList(), emptyList(), emptyList(), currentFilter.value, ResourceState.Loading)
   )

   init {
      syncStateDatabase()
   }

   fun onEvent(event: HomeEvent) {
      when (event) {
         is HomeEvent.OnChangeFilter -> {
            currentFilter.value = event.filter
         }

         is HomeEvent.OnChangeSortType -> {
            val newFilter = when (currentFilter.value) {
               is ProductFilter.ByPrice -> ProductFilter
                  .ByPrice(event.sortType)

               is ProductFilter.ByDiscountPercentage -> ProductFilter
                  .ByDiscountPercentage(event.sortType)

               is ProductFilter.ByCategory -> ProductFilter
                  .ByCategory(
                     (currentFilter.value as ProductFilter.ByCategory).category,
                     sortType = event.sortType,
                  )

               is ProductFilter.ByRating -> ProductFilter
                  .ByRating(event.sortType)

               is ProductFilter.ByStock -> ProductFilter
                  .ByStock(event.sortType)

               is ProductFilter.ByBrand -> ProductFilter
                  .ByBrand(
                     (currentFilter.value as ProductFilter.ByBrand).brand,
                     sortType = event.sortType,
                  )

               is ProductFilter.ByTitle -> ProductFilter
                  .ByTitle(
                     (currentFilter.value as ProductFilter.ByTitle).title,
                     sortType = event.sortType,
                  )
            }
            currentFilter.value = newFilter
         }

         HomeEvent.OnResetSync -> {
            syncStateDatabase()
         }
      }
   }

   private fun sendUiEvent(event: HomeUiEvent) {
      viewModelScope.launch {
         _uiEvent.send(event)
      }
   }

   private fun syncStateDatabase() {
      viewModelScope.launch {
         stateSync.value = ResourceState.Loading
         val isSuccess = productUseCases.syncDatabaseUseCase()
         if (isSuccess) {
            stateSync.value = ResourceState.Success(System.currentTimeMillis())
         } else {
            stateSync.value = ResourceState.Error
         }
      }
   }

   internal data class HomeStateData(
      val categories: List<Category>,
      val brands: List<String>,
      val filter: ProductFilter,
      val syncState: ResourceState,
   )

   sealed interface ResourceState {
      data object Loading : ResourceState
      data object Error : ResourceState
      data class Success(val now: Long) : ResourceState
   }
}
