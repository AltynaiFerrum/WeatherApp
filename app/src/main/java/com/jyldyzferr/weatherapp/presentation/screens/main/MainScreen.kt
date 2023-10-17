package com.jyldyzferr.weatherapp.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jyldyzferr.weatherapp.R
import com.jyldyzferr.weatherapp.presentation.components.HourlyWeatherItemList
import com.jyldyzferr.weatherapp.presentation.models.CountryInfo
import com.jyldyzferr.weatherapp.presentation.models.WeatherDayInfoUi
import com.jyldyzferr.weatherapp.presentation.utils.DateType
import com.jyldyzferr.weatherapp.presentation.utils.fetchDayType
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navigateToWeatherList: () -> Unit,
    uiStateFlow: StateFlow<MainScreenUiState>,
    modifier: Modifier = Modifier
) {
    val uiState = uiStateFlow.collectAsStateWithLifecycle().value

    val fullScreenModifier = modifier
        .fillMaxSize()

    if (uiState is MainScreenUiState.Error) {
        ErrorMainScreen(
            errorMessage = "",
            modifier = fullScreenModifier
        )
    }
    MainScreen(
        navigateToWeatherList = navigateToWeatherList,
        uiState = uiState
    )

}

@Composable
private fun MainScreen(
    uiState: MainScreenUiState,
    navigateToWeatherList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        val isLoaded = uiState is MainScreenUiState.Loaded
        val backgroundImageId = when (fetchDayType()) {
            DateType.SUNSET -> R.drawable.sunset
            DateType.LIGHT -> R.drawable.sunrise
            DateType.NIGHT -> R.drawable.night
        }
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = backgroundImageId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        when (uiState) {
            is MainScreenUiState.Loading -> LoadingBlock()
            is MainScreenUiState.Loaded -> {

                LoadedBlock(
                    currentWeather = uiState.weather.currentWeather,
                    countryInfo = uiState.countryInfo
                )
            }

            else -> Unit

        }

        IconButton(
            modifier = Modifier
                .statusBarsPadding()
                .align(Alignment.TopEnd)
                .padding(top = 20.dp, start = 24.dp)
                .size(32.dp),
            onClick = navigateToWeatherList
        ) {
            Icon(
                painter = painterResource(id = R.drawable.menu_icon),
                contentDescription = null,
                tint = Color.White
            )
        }

    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BoxScope.LoadedBlock(
    currentWeather: WeatherDayInfoUi,
    countryInfo: CountryInfo,
    modifier: Modifier = Modifier
) {

    var isShowInfoBlock by remember { mutableStateOf(false) }

    rememberCoroutineScope().launch() {
        kotlinx.coroutines.delay(100)
        isShowInfoBlock = true
    }

    AnimatedVisibility(
        modifier = Modifier.align(Alignment.TopCenter),
        visible = isShowInfoBlock,
        enter = fadeIn(
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = fadeOut(
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        WeatherInfo(
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 52.dp)
                .align(Alignment.TopCenter),
            city = countryInfo.cityName,
            temperature = "${currentWeather.temperature}",
            weatherType = "${currentWeather.weatherConditionType}",
            windSpeed = "${currentWeather.windSpeed}"
        )
    }
    AnimatedVisibility(
        modifier = Modifier.align(Alignment.BottomCenter),
        visible = isShowInfoBlock,
        enter = slideInVertically(
            animationSpec = tween(1000),
            initialOffsetY = {
                it / 2
            }
        ),
        exit = slideOutVertically(
            animationSpec = tween(1000),
            targetOffsetY = {
                it / 2
            }
        )
    ) {
        HourlyWeatherItemList(
            weatherHours = currentWeather.hourlyWeathers,
        )
    }
}


@Composable
fun BoxScope.LoadingBlock(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .statusBarsPadding()
            .padding(top = 52.dp)
            .fillMaxWidth()
            .height(300.dp)
            .align(Alignment.TopCenter),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMainScreen(errorMessage: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = errorMessage, style = MaterialTheme.typography.titleLarge)
    }
}


@Composable
fun WeatherInfo(
    city: String,
    temperature: String,
    weatherType: String,
    windSpeed: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = city,
            fontSize = 44.sp,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = temperature,
            fontSize = 50.sp,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = weatherType,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = windSpeed,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}
