package com.textsdev.cleanweatherapp_mvvm.data.repository

import com.textsdev.cleanweatherapp_mvvm.BuildConfig
import com.textsdev.cleanweatherapp_mvvm.data.model.CurrentWeatherOfflineModel
import com.textsdev.cleanweatherapp_mvvm.data.model.WeatherForecastOfflineModel
import com.textsdev.cleanweatherapp_mvvm.network.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

class WeatherRepositoryImpl(private val apiService: WeatherApiService) : WeatherRepository {
    override suspend fun getCurrentWeather(cityName: String): WeatherResult<CurrentWeatherOfflineModel> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getCurrentWeather(cityName, BuildConfig.API_KEY)
            if (response.isSuccessful) {
                val weatherResponse = response.body()
                WeatherResult.Success(
                    CurrentWeatherOfflineModel(
                        cityName = weatherResponse?.name ?: "",
                        temperature = weatherResponse?.main?.temp ?: 0.0
                    )
                )
            } else {
                WeatherResult.Error("Error occurred while fetching weather data: ${response.code()}")
            }
        }
    }

    override suspend fun getWeatherForecast(cityName: String): WeatherResult<List<WeatherForecastOfflineModel>> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getWeatherForecast(cityName, BuildConfig.API_KEY)
            if (response.isSuccessful) {
                val weatherResponse = response.body()
                val weatherForecastOfflineModelList: List<WeatherForecastOfflineModel> =
                    weatherResponse?.list?.map {
                        WeatherForecastOfflineModel(
                            date = it.dt,
                            temp = it.temp.day.roundToInt(),
                        )
                    } ?: emptyList()
                WeatherResult.Success(weatherForecastOfflineModelList)
            } else {
                WeatherResult.Error("Error occurred while fetching weather data: ${response.code()}")
            }
        }
    }
}

sealed class WeatherResult<out T> {
    data class Success<T>(val data: T) : WeatherResult<T>()
    data class Error(val message: String) : WeatherResult<Nothing>()
}