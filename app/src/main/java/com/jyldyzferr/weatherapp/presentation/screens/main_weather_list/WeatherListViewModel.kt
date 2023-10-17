package com.jyldyzferr.weatherapp.presentation.screens.main_weather_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jyldyzferr.weatherapp.domain.use_cases.FetchWeathersUseCases
import com.jyldyzferr.weatherapp.presentation.utils.mappers.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val fetchWeathersUseCases: FetchWeathersUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherListUiState>(WeatherListUiState.Loading)
    val uiState: StateFlow<WeatherListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.tryEmit(WeatherListUiState.Loading)
            val weather = fetchWeathersUseCases.invoke()
            val dailyWeathers = weather.first.otherWeathers.map { it.toUi() }
            _uiState.tryEmit(WeatherListUiState.Loaded(dailyWeathers, weather.second))
        }
    }
}