package com.jyldyzferr.weatherapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Daily(
    @SerializedName("time")
    val time: List<String>,
    @SerializedName ("weathercode")
    val weatherСode: List<Int>,
    @SerializedName ("windspeed_10m_max")
    val windspeedMax: List<Double>
): Parcelable