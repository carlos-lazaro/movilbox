package com.example.mobilbox.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.mobilbox.data.local.database.AppDatabase
import com.example.mobilbox.data.local.database.dao.CategoryEntityDao
import com.example.mobilbox.data.local.database.dao.ProductEntityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseLocalModule {

   @Provides
   @Singleton
   fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
      return Room.databaseBuilder(
         appContext,
         AppDatabase::class.java,
         "AppDatabase"
      ).build()
   }

   @Provides
   fun provideProductEntityDao(appDatabase: AppDatabase): ProductEntityDao {
      return appDatabase.productEntityDao()
   }

   @Provides
   fun provideCategoryEntityDao(appDatabase: AppDatabase): CategoryEntityDao {
      return appDatabase.categoryEntityDao()
   }
}
