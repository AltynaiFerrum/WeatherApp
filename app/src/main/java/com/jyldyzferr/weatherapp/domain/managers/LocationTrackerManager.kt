package com.jyldyzferr.weatherapp.domain.managers

import android.location.Location

interface LocationTrackerManager {

    suspend fun fetchCurrentLocation(): Location?
}