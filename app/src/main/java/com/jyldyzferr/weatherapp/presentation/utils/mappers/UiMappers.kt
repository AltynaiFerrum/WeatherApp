package com.jyldyzferr.weatherapp.presentation.utils.mappers

import com.jyldyzferr.weatherapp.domain.models.WeatherDayInfoDomain
import com.jyldyzferr.weatherapp.domain.models.WeatherDomain
import com.jyldyzferr.weatherapp.domain.models.WeatherHourInfoDomain
import com.jyldyzferr.weatherapp.presentation.models.WeatherConditionType
import com.jyldyzferr.weatherapp.presentation.models.WeatherDayInfoUi
import com.jyldyzferr.weatherapp.presentation.models.WeatherHourInfoUi
import com.jyldyzferr.weatherapp.presentation.models.WeatherUi

fun WeatherHourInfoDomain.toUi(): WeatherHourInfoUi{
    return this.run {
        WeatherHourInfoUi(
            temperature = temperature,
            weatherConditionType = WeatherConditionType.findWeatherTypeByCode(weatherCode),
            windSpeed = windSpeed,
            date = date
        )
    }
}

fun WeatherDayInfoDomain.toUi(): WeatherDayInfoUi {
    return if (this == WeatherDayInfoDomain.unknown)
        WeatherDayInfoUi.unknown
    else this.run {
        WeatherDayInfoUi(
            temperature = temperature,
            weatherConditionType = WeatherConditionType.findWeatherTypeByCode(weatherCode),
            windSpeed = windSpeed,
            time = time,
            hourlyWeathers = hourlyWeathers.map { it.toUi() }
        )
    }
}

fun WeatherDomain.toUi(): WeatherUi{
    return if (this.isUnknown()) WeatherUi.unknown
    else WeatherUi(
        currentWeather = currentWeather.toUi(),
        otherWeathers= otherWeathers.map{it.toUi()}
    )
}