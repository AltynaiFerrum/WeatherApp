package com.jyldyzferr.weatherapp.presentation.screens.main_weather_list

import com.jyldyzferr.weatherapp.presentation.models.CountryInfo
import com.jyldyzferr.weatherapp.presentation.models.WeatherDayInfoUi

sealed class WeatherListUiState {

    object Loading : WeatherListUiState()

    data class Loaded(
        val dailyWeathers: List<WeatherDayInfoUi>,
        val countryInfo: CountryInfo
    ) : WeatherListUiState()

   data class Error (
        val message: String
    ): WeatherListUiState()
}