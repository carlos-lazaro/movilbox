package com.example.mobilbox.ui.screen.product.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilbox.domain.usecase.product.ProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
        private val productUseCases : ProductUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailState>(
        ProductDetailState()
    )
    val uiState : StateFlow<ProductDetailState> = _uiState.asStateFlow()

    fun onEvent(event : ProductDetailEvent) {
        when (event) {
            is ProductDetailEvent.OnSetId -> {
                viewModelScope.launch {
                    getProduct(event.id)
                }

            }
        }
    }

    private suspend fun getProduct(id : Int? = null) {
        id?.let {
            productUseCases.getProductByIdUseCase(it).let { product ->
                _uiState.update {
                    _uiState.value.copy(product = product)
                }
            }
        }
    }
}
