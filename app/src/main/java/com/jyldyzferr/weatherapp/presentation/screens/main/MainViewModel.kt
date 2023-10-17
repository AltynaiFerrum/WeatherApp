package com.jyldyzferr.weatherapp.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jyldyzferr.weatherapp.domain.use_cases.FetchWeathersUseCases
import com.jyldyzferr.weatherapp.presentation.models.CountryInfo
import com.jyldyzferr.weatherapp.presentation.models.WeatherUi
import com.jyldyzferr.weatherapp.presentation.utils.mappers.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MainScreenUiState {
    object Loading : MainScreenUiState()

    data class Loaded(
        val weather: WeatherUi,
        val countryInfo: CountryInfo
    ) : MainScreenUiState()

    data class Error(
        val message: String
    ) : MainScreenUiState()
}


@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchWeathersUseCases: FetchWeathersUseCases,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    fun fetchCurrentWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.tryEmit(MainScreenUiState.Loading)
            val weatherParams = fetchWeathersUseCases.invoke()
            val weather = weatherParams.first.toUi()

            _uiState.tryEmit(
                if (weather.isUnknown()) MainScreenUiState.Error("Что-то пошло не так")
                else MainScreenUiState.Loaded(weather, weatherParams.second)
            )
        }
    }
}

