package com.jyldyzferr.weatherapp.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.jyldyzferr.weatherapp.data.remote.WeatherService
import com.jyldyzferr.weatherapp.domain.WeatherRepository
import com.jyldyzferr.weatherapp.domain.models.WeatherDayInfoDomain
import com.jyldyzferr.weatherapp.domain.models.WeatherDomain
import com.jyldyzferr.weatherapp.domain.models.WeatherHourInfoDomain
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

data class WeatherDataInfo(
    val date: Date,
    val temperature: Double,
    val weatherCode: Int,
    val windSpeed: Double,
)


class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
) : WeatherRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun fetchWeatherFor16Days(
        longitude: String, latitude: String
    ): WeatherDomain {
        try {
            val weatherResponse = weatherService.fetchWeatherFor16Day(
                longitude = longitude, latitude = latitude
            )
            val weatherRemote = weatherResponse.body() ?:
            return WeatherDomain.unknown

            val allWeathersByDay = weatherRemote.hourly.time.mapIndexed { index, time ->

                val temperature = if (weatherRemote.hourly.temperature.size > index) {
                    weatherRemote.hourly.temperature[index]
                } else {
                    0.0
                }

                val weatherCode = if (weatherRemote.hourly.weatherСode.size > index) {
                    weatherRemote.hourly.weatherСode[index]
                } else {
                    -1
                }

                val windSpeed = if (weatherRemote.hourly.windspeed.size > index) {
                    weatherRemote.hourly.windspeed[index]
                } else {
                    0.0
                }

                val localDate = LocalDateTime.parse(time)
                val date = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant())

                WeatherDataInfo(
                    date = date,
                    temperature = temperature,
                    weatherCode = weatherCode,
                    windSpeed = windSpeed,
                )
            }.groupBy { it.date.formatAsDayMonthYear() }


            val currentDayKey =
                allWeathersByDay.keys.firstOrNull { it == Date().formatAsDayMonthYear() }

            val info = allWeathersByDay[currentDayKey]

            val currentWeather = WeatherDayInfoDomain(
                temperature = info?.map { it.temperature }?.firstOrNull() ?: 0.0,
                weatherCode = info?.map { it.weatherCode }?.firstOrNull() ?: -1,
                windSpeed = info?.map { it.windSpeed }?.firstOrNull() ?: 0.0,
                time = currentDayKey.toString(),
                hourlyWeathers = info?.map(::weatherDataInfoToDomain) ?: emptyList()
            )

            val weatherFor15Days = allWeathersByDay.map { weatherInfo ->
                WeatherDayInfoDomain(
                    temperature = weatherInfo.value.map { it.temperature }.firstOrNull() ?: 0.0,
                    weatherCode = weatherInfo.value.map { it.weatherCode }.firstOrNull() ?: -1,
                    windSpeed = weatherInfo.value.map { it.windSpeed }.firstOrNull() ?: 0.0,
                    time = weatherInfo.key,
                    hourlyWeathers = weatherInfo.value.map(::weatherDataInfoToDomain)
                )
            }
            return WeatherDomain(
                currentWeather = currentWeather, otherWeathers = weatherFor15Days
            )
        } catch (e: Throwable) {
            Log.e("Jyldyz", e.message.toString())
            return WeatherDomain.unknown
        }
    }

    private fun weatherDataInfoToDomain(
        weatherDataInfo: WeatherDataInfo
    ): WeatherHourInfoDomain = weatherDataInfo.run {
        WeatherHourInfoDomain(
            date = date,
            temperature = temperature,
            weatherCode = weatherCode,
            windSpeed = windSpeed,
        )
    }


    fun Date.formatAsDayMonthYear(): String {
        val format = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        format.timeZone = TimeZone.getTimeZone("GMT")
        return format.format(this)
    }
}