package com.example.mobilbox.domain.di

import com.example.mobilbox.domain.repository.ProductRepository
import com.example.mobilbox.domain.usecase.ProductUseCases
import com.example.mobilbox.domain.usecase.product.DeleteProductByIdUseCase
import com.example.mobilbox.domain.usecase.product.GetProductBrandsUseCase
import com.example.mobilbox.domain.usecase.product.GetProductByIdUseCase
import com.example.mobilbox.domain.usecase.product.GetProductCategoriesUseCase
import com.example.mobilbox.domain.usecase.product.GetProductsUseCase
import com.example.mobilbox.domain.usecase.product.SyncDatabaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

   @Provides
   @Singleton
   fun provideProductUseCases(repository: ProductRepository): ProductUseCases {
      return ProductUseCases(
         syncDatabaseUseCase = SyncDatabaseUseCase(repository),
         getProductsUseCase = GetProductsUseCase(repository),
         getProductByIdUseCase = GetProductByIdUseCase(repository),
         getProductCategoriesUseCase = GetProductCategoriesUseCase(repository),
         getProductBrandsUseCase = GetProductBrandsUseCase(repository),
         deleteProductByIdUseCase = DeleteProductByIdUseCase(repository)
      )
   }
}
