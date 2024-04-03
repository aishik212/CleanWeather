package com.textsdev.cleanweatherapp_mvvm.data.repository

import com.textsdev.cleanweatherapp_mvvm.BuildConfig
import com.textsdev.cleanweatherapp_mvvm.data.model.CurrentWeatherOfflineModel
import com.textsdev.cleanweatherapp_mvvm.data.model.WeatherForecastOfflineModel
import com.textsdev.cleanweatherapp_mvvm.network.ApiException
import com.textsdev.cleanweatherapp_mvvm.network.WeatherApiService

class WeatherRepositoryImpl(private val apiService: WeatherApiService) : WeatherRepository {
    override suspend fun getCurrentWeather(cityName: String): CurrentWeatherOfflineModel {
        val response = apiService.getCurrentWeather(cityName, BuildConfig.API_KEY)
        if (response.isSuccessful) {
            val weatherResponse = response.body()
            // Assuming WeatherResponse contains necessary data
            return CurrentWeatherOfflineModel(
                cityName = weatherResponse?.name ?: "",
                temperature = weatherResponse?.main?.temp ?: 0.0,
            )
        } else {
            throw ApiException("Error occurred while fetching weather data: ${response.code()}")
        }
    }

    override suspend fun getWeatherForecast(cityName: String): List<WeatherForecastOfflineModel> {
        val response = apiService.getWeatherForecast(cityName, BuildConfig.API_KEY)
        if (response.isSuccessful) {
            val weatherResponse = response.body()
            // Assuming WeatherResponse contains necessary data
            val weatherForecastOfflineModelList: List<WeatherForecastOfflineModel> =
                weatherResponse?.list?.map {
                    WeatherForecastOfflineModel(
                        date = it.dt,
                        temp = it.main.temp,
                    )
                } ?: emptyList()
            return weatherForecastOfflineModelList
        } else {
            throw ApiException("Error occurred while fetching weather data: ${response.code()}")
        }
    }
}