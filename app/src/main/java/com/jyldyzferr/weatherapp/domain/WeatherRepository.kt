package com.jyldyzferr.weatherapp.domain

import com.jyldyzferr.weatherapp.domain.models.WeatherDomain

interface WeatherRepository {

   suspend fun fetchWeatherFor16Days(
      longitude: String,
      latitude: String
   ): WeatherDomain
}