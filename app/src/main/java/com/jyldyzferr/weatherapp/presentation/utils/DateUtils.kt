package com.jyldyzferr.weatherapp.presentation.utils

import java.util.Date


enum class DateType {
    LIGHT, NIGHT, SUNSET,
}

fun fetchDayType(): DateType
= when (Date().hours) {
    in 20..23 -> DateType.NIGHT
    in 0.. 6 -> DateType.NIGHT
    in 8.. 18 -> DateType.LIGHT
    7 -> DateType.SUNSET
    19 -> DateType.SUNSET
    else -> DateType.LIGHT
}