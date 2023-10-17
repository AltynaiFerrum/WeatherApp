package com.jyldyzferr.weatherapp.domain.models

import java.time.LocalDate
import java.util.Date

data class WeatherHourInfoDomain(
    val temperature: Double,
    val weatherCode: Int,
    val date: Date,
    val windSpeed: Double,
)

data class WeatherDayInfoDomain (
    val temperature: Double,
    val weatherCode: Int,
    val time: String,
    val windSpeed: Double,
    val hourlyWeathers: List<WeatherHourInfoDomain>
){
    companion object{

       val unknown = WeatherDayInfoDomain(
           temperature = 0.0,
           weatherCode = -1,
           windSpeed = 0.0,
           time = "unknown",
           hourlyWeathers = emptyList()
       )
    }
}