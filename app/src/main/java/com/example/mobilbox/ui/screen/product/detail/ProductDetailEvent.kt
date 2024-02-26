package com.example.mobilbox.ui.screen.product.detail

sealed interface ProductDetailEvent {

    data class OnSetId(val id : Int? = null) : ProductDetailEvent
}
