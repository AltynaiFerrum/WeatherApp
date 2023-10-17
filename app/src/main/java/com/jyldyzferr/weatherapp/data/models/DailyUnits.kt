package com.jyldyzferr.weatherapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyUnits(
    @SerializedName ("time")
    val time: String,
    @SerializedName ("weathercode")
    val weatherCode: String,
    @SerializedName ("windspeed_10m_max")
    val windspeedMax: String
) :Parcelable