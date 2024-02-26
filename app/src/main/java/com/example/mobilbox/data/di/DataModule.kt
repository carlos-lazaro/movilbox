package com.example.mobilbox.data.di

import com.example.mobilbox.data.repository.ProductEntityRepository
import com.example.mobilbox.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun productRepository(productRepository : ProductEntityRepository) : ProductRepository {
        return productRepository
    }
}
