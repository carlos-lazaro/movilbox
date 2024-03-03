package com.example.mobilbox.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobilbox.data.local.database.dao.CategoryEntityDao
import com.example.mobilbox.data.local.database.dao.ProductEntityDao
import com.example.mobilbox.data.local.database.model.CategoryEntity
import com.example.mobilbox.data.local.database.model.ProductEntity

@Database(
   entities = [
      ProductEntity::class,
      CategoryEntity::class,
   ], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

   abstract fun productEntityDao(): ProductEntityDao
   abstract fun categoryEntityDao(): CategoryEntityDao
}
