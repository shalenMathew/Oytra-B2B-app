package com.example.oytra.util.di

import com.example.oytra.data.OytraApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun createRetrofitInstance(): Retrofit{
      return  Retrofit.Builder()
            .baseUrl("https://gist.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun getOytraService(retrofit: Retrofit): OytraApiService{
        return retrofit.create(OytraApiService::class.java)
    }

}