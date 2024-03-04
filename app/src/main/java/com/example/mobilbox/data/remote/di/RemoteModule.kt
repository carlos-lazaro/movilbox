package com.example.mobilbox.data.remote.di

import com.example.mobilbox.BuildConfig
import com.example.mobilbox.data.remote.service.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

   @Singleton
   @Provides
   fun provideOkHttpClient(): OkHttpClient {
      val loggingInterceptor = HttpLoggingInterceptor()
      loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
      return OkHttpClient.Builder()
         .addInterceptor(loggingInterceptor)
         .build()
   }

   @Singleton
   @Provides
   fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
      return Retrofit.Builder()
         .baseUrl(BuildConfig.BASE_URL)
         .client(okHttpClient)
         .addConverterFactory(MoshiConverterFactory.create())
         .build()
   }

   @Provides
   fun provideApiService(retrofit: Retrofit): ProductService {
      return retrofit.create(ProductService::class.java)
   }
}
