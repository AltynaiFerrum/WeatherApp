package com.jyldyzferr.weatherapp.presentation.models

import androidx.annotation.DrawableRes
import com.jyldyzferr.weatherapp.R

enum class WeatherConditionType(
    val description: String,
    val weatherCodes: List<Int>,
    @DrawableRes val lightImageID: Int,
    @DrawableRes val nightImageID: Int,
) {

    CLEAR_SKY(
        description = "Clear sky",
        weatherCodes = listOf(0),
        lightImageID = R.drawable.clear_sky_light,
        nightImageID = R.drawable.clear_sky_night
    ),
    MAINLY_CLEAR(
        description = "Mainly clear",
        weatherCodes = listOf(1),
        lightImageID = R.drawable.mainly_clear_light,
        nightImageID = R.drawable.mainly_clear_night
    ),
    PARTLY_CLOUDY(
        description = "Partly cloudy",
        weatherCodes = listOf(2),
        lightImageID = R.drawable.partly_cloudy_light,
        nightImageID = R.drawable.partly_cloudy_night
    ),
    OVERCAST(
        description = "Overcast",
        weatherCodes = listOf(3),
        lightImageID = R.drawable.overcast_light,
        nightImageID = R.drawable.overcast_night
    ),
    FOG(
        description = "Fog",
        weatherCodes = listOf(45),
        lightImageID = R.drawable.overcast_light,
        nightImageID = R.drawable.overcast_night
    ),
    DEPOSITING_FOG(
        description = "Depositing fog",
        weatherCodes = listOf(48),
        lightImageID = R.drawable.overcast_light,
        nightImageID = R.drawable.overcast_night
    ),
    RAINY_SMALL(
        description = "Light rain",
        weatherCodes = listOf(51,56, 61),
        lightImageID = R.drawable.rain_small_light,
        nightImageID = R.drawable.rain_small_night
    ),
    RAINY_MEDIUM(
        description = "Moderate rain",
        weatherCodes = listOf(53,63),
        lightImageID = R.drawable.rain_medium_light,
        nightImageID = R.drawable.rain_medium_night
    ),
    RAINY_HIGHT(
        description = "Intensity rain",
        weatherCodes = listOf(55,57,65),
        lightImageID = R.drawable.rain_hight_light,
        nightImageID = R.drawable.rain_hight_night
    ),
    FREEZING_RAIN_SMALL(
        description = "Light freezing rain",
        weatherCodes = listOf(66),
        lightImageID = R.drawable.freezing_rain_small_light,
        nightImageID = R.drawable.freezing_rain_small_night
    ),
    FREEZING_RAIN_MEDIUM(
        description = "Moderate freezing rain",
        weatherCodes = listOf(67),
        lightImageID = R.drawable.freezing_rain_medium,
        nightImageID = R.drawable.freezing_rain_medium
    ),
    SNOW_SMALL(
        description = "Light snow",
        weatherCodes = listOf(71, 80, 85),
        lightImageID = R.drawable.snow_small,
        nightImageID = R.drawable.snow_small
    ),
    SNOW_MEDIUM(
        description = "Moderate snow",
        weatherCodes = listOf(73, 81, 86),
        lightImageID = R.drawable.snom_medium_light,
        nightImageID = R.drawable.snow_medium_night
    ),
    SNOW_HIGT(
        description = "Intensity snow",
        weatherCodes = listOf(75, 77, 82),
        lightImageID = R.drawable.snow_hight,
        nightImageID = R.drawable.snow_hight
    ),
    STORM_SMALL(
        description = "Light storm",
        weatherCodes = listOf(96),
        lightImageID = R.drawable.storm_small_light,
        nightImageID = R.drawable.storm_small_night
    ),
    STORM_MEDIUM(
        description = "Moderate storm",
        weatherCodes = listOf(95, 99),
        lightImageID = R.drawable.storm_medium_light,
        nightImageID = R.drawable.storm_medium_night
    ),
    UNKNOWN(
        description = "Unknown",
        weatherCodes = listOf(-1),
        lightImageID = R.drawable.clear_sky_light,
        nightImageID = R.drawable.clear_sky_night
    );

    companion object {
        fun findWeatherTypeByCode(weatherCode:Int): WeatherConditionType =
            values().find { weatherConditionType ->
                weatherConditionType.weatherCodes.contains(weatherCode)
            }?: UNKNOWN
    }
}