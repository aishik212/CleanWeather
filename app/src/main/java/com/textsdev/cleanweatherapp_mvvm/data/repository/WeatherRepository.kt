package com.textsdev.cleanweatherapp_mvvm.data.repository

import com.textsdev.cleanweatherapp_mvvm.data.model.CurrentWeatherOfflineModel
import com.textsdev.cleanweatherapp_mvvm.data.model.WeatherForecastOfflineModel

interface WeatherRepository {
    suspend fun getCurrentWeather(cityName: String): WeatherResult<CurrentWeatherOfflineModel>
    suspend fun getWeatherForecast(cityName: String): WeatherResult<List<WeatherForecastOfflineModel>>
}
