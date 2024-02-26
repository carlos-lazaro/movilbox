package com.example.mobilbox.domain.usecase.product

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.graphics.vector.ImageVector

enum class SortType(val value : Int, val icon : ImageVector) {
    ASC(0, Icons.Filled.KeyboardArrowUp),
    DESC(1, Icons.Filled.KeyboardArrowDown),
}
