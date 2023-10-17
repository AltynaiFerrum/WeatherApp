package com.jyldyzferr.weatherapp.di

import com.jyldyzferr.weatherapp.data.remote.WeatherService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 class RetrofitModule {

    @Provides
    fun provideRetrofit(): Retrofit{
       return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            ).build()
    }

    @Provides
    fun provideWeatherService(
        retrofit: Retrofit
    ): WeatherService = retrofit.create(WeatherService::class.java)
}