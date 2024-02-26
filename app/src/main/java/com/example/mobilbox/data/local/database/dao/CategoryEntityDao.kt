package com.example.mobilbox.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.mobilbox.data.local.database.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryEntityDao {

    @Query("SELECT * FROM categories")
    fun getCategories() : Flow<List<CategoryEntity>>

    @Upsert
    suspend fun upsertCategories(categories : List<CategoryEntity>)
}
