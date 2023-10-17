package com.jyldyzferr.weatherapp.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {

    val route: String
    val routeWithArgs: String
}

object MainScreen: Destination{
    override val route: String = "main_screen"

    override val routeWithArgs: String = route
}

object WeatherListScreen: Destination{
    override val route: String = "weather_list_screen"

    override val routeWithArgs: String = route
}

