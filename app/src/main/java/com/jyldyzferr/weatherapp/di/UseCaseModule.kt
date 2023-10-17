package com.jyldyzferr.weatherapp.di

import android.app.Application
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.jyldyzferr.weatherapp.domain.WeatherRepository
import com.jyldyzferr.weatherapp.domain.managers.LocationImpl
import com.jyldyzferr.weatherapp.domain.managers.LocationTrackerManager
import com.jyldyzferr.weatherapp.domain.use_cases.FetchWeathersUseCases
import com.jyldyzferr.weatherapp.domain.use_cases.FetchWeathersUseCasesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideFetchWeathersUseCases(
        @ApplicationContext context: Context,
        repository: WeatherRepository,
        locationTrackerManager: LocationTrackerManager
    ): FetchWeathersUseCases = FetchWeathersUseCasesImpl(
        repository = repository,
        locationTrackerManager = locationTrackerManager,
        context = context
    )
}