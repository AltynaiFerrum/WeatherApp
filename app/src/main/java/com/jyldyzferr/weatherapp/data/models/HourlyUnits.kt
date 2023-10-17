package com.jyldyzferr.weatherapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HourlyUnits(
    @SerializedName("temperature_2m")
    val temperature: String,
    @SerializedName ("time")
    val time: String,
    @SerializedName ("weathercode")
    val weatherCode: String,
    @SerializedName ("windspeed_10m")
    val windspeed: String
): Parcelable