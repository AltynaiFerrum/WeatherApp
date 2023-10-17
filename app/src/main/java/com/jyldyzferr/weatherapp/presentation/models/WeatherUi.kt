package com.jyldyzferr.weatherapp.presentation.models

data class WeatherUi (

    val currentWeather: WeatherDayInfoUi,
    val otherWeathers: List<WeatherDayInfoUi>
){
    fun isUnknown() = this == unknown

    companion object {

        val unknown = WeatherUi(
            currentWeather = WeatherDayInfoUi.unknown,
            otherWeathers = listOf(WeatherDayInfoUi.unknown)
        )
    }
}