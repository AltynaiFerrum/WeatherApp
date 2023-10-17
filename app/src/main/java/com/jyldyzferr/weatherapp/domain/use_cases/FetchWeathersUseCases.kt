package com.jyldyzferr.weatherapp.domain.use_cases

import com.jyldyzferr.weatherapp.domain.models.WeatherDomain
import com.jyldyzferr.weatherapp.presentation.models.CountryInfo

interface FetchWeathersUseCases {

    suspend operator fun invoke(): Pair<WeatherDomain, CountryInfo>
}