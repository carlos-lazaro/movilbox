package com.example.mobilbox.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mobilbox.domain.model.Category

@Entity(tableName = "categories")
data class CategoryEntity(
   @PrimaryKey
   @ColumnInfo("id")
   val id: Int,
   @ColumnInfo("name")
   val name: String,
)

fun CategoryEntity.toCategory() = Category(
   id = id,
   name = name,
)
