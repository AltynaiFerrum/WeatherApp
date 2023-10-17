package com.jyldyzferr.weatherapp.presentation.models

import java.util.Date

data class WeatherHourInfoUi(
    val temperature: Double,
    val weatherConditionType: WeatherConditionType,
    val date: Date,
    val windSpeed: Double,
){
    companion object {

        val unknown = WeatherHourInfoUi(
            temperature = 0.0,
            weatherConditionType = WeatherConditionType.UNKNOWN,
            windSpeed = 0.0,
            date = Date(),
        )
    }
}

data class WeatherDayInfoUi(
    val temperature: Double,
    val weatherConditionType: WeatherConditionType,
    val time: String,
    val windSpeed: Double,
    val hourlyWeathers: List<WeatherHourInfoUi>
) {
    companion object {

        val unknown = WeatherDayInfoUi(
            temperature = 0.0,
            weatherConditionType = WeatherConditionType.UNKNOWN,
            windSpeed = 0.0,
            time = "unknown",
            hourlyWeathers = emptyList()
        )
    }

}