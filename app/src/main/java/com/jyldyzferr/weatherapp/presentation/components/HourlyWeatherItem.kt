package com.jyldyzferr.weatherapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jyldyzferr.weatherapp.R
import com.jyldyzferr.weatherapp.presentation.models.WeatherHourInfoUi
import com.jyldyzferr.weatherapp.presentation.utils.DateType
import com.jyldyzferr.weatherapp.presentation.utils.fetchDayType
import java.util.Date

@Preview
@Composable
fun HourlyWeatherItemPreview() {
    MaterialTheme {
        HourlyWeatherItem(
            weatherHourInfoUi = WeatherHourInfoUi.unknown
        )
    }
}

@Preview
@Composable
fun HourlyWeatherItemListPreview() {
    MaterialTheme {
        HourlyWeatherItemList(
            weatherHours = listOf(
                WeatherHourInfoUi.unknown,
                WeatherHourInfoUi.unknown,
                WeatherHourInfoUi.unknown,
                WeatherHourInfoUi.unknown,
            )
        )
    }
}

@Composable
fun HourlyWeatherItemList(
    weatherHours: List<WeatherHourInfoUi>,
    modifier: Modifier = Modifier,
) {
    val filtredWeathers = mutableListOf<WeatherHourInfoUi>()

    val currentWeather = weatherHours.filter {
        it.date.hours == Date().hours
    }.firstOrNull()

    filtredWeathers.addAll(weatherHours.filter {
        it.date.hours != Date().hours })
    if (currentWeather != null) filtredWeathers.add(0,currentWeather)

    Box(modifier = modifier.height(270.dp)) {
        Image(
            modifier = modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )
        LazyRow(
            modifier = modifier.align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(
                items = filtredWeathers,
                key = { it.hashCode() }
            ) { weather ->
                HourlyWeatherItem(weatherHourInfoUi = weather)
            }
        }
    }
}


@Composable
fun HourlyWeatherItem(
    weatherHourInfoUi: WeatherHourInfoUi,
    modifier: Modifier = Modifier
) {
    val isNow = weatherHourInfoUi.date.hours == Date().hours

    val NightBlue = Color(0xFF48319D)
    val LightBlue = Color(0xFF40CBD8)

    val actualColor =  NightBlue

    var isWeatherLight = false
    when (weatherHourInfoUi.date.hours) {
        in 19..23 -> isWeatherLight = false
        in 0..6 -> isWeatherLight = false
        in 7..18 -> isWeatherLight = true
    }

    Box(
        modifier = modifier
            .height(176.dp)
            .width(75.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(
                1.dp, actualColor, RoundedCornerShape(20.dp)
            )
            .background(
                if (isNow) actualColor
                else actualColor.copy(alpha = 0.4f)
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = if (isNow) "Now"
                else "${weatherHourInfoUi.date.hours}h",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )

            Image(
                modifier = Modifier
                    .height(38.dp)
                    .width(44.dp), painter = painterResource(
                    id = if (isWeatherLight) weatherHourInfoUi.weatherConditionType.lightImageID
                    else weatherHourInfoUi.weatherConditionType.nightImageID
                ), contentDescription = null
            )
            Text(
                text = "${weatherHourInfoUi.temperature}°",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}