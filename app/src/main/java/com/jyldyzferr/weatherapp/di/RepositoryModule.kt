package com.jyldyzferr.weatherapp.di

import com.jyldyzferr.weatherapp.data.remote.WeatherService
import com.jyldyzferr.weatherapp.data.repository.WeatherRepositoryImpl
import com.jyldyzferr.weatherapp.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideWeatherRepository(
    weatherService: WeatherService
    ): WeatherRepository = WeatherRepositoryImpl(
        weatherService
    )
}