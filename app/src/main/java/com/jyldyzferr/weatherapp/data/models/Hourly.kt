package com.jyldyzferr.weatherapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hourly(
    @SerializedName("temperature_2m")
    val temperature: List<Double>,
    @SerializedName ("time")
    val time: List<String>,
    @SerializedName ("weathercode")
    val weatherСode: List<Int>,
    @SerializedName ("windspeed_10m")
    val windspeed: List<Double>
): Parcelable