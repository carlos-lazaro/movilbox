package com.example.mobilbox.data.remote.service.dto

import com.example.mobilbox.data.remote.model.ProductResource
import com.squareup.moshi.Json

class GetProductResources {

    @Json(name = "products")
    var products : List<ProductResource> = emptyList()

    @Json(name = "total")
    var total : Int? = null

    @Json(name = "skip")
    var skip : Int? = null

    @Json(name = "limit")
    var limit : Int? = null
}
