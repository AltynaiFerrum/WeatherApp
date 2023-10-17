package com.jyldyzferr.weatherapp.presentation.models

data class CountryInfo (

    val countryName: String,
    val cityName: String,

){

    fun fetchFullInfo() = "$countryName\n$cityName"

    companion object{
        val unknown = CountryInfo(
            cityName = "Unknown",
            countryName = "Unknown"
        )
    }
}