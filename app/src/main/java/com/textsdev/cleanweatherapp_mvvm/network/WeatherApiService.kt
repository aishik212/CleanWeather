package com.textsdev.cleanweatherapp_mvvm.network

import com.textsdev.cleanweatherapp_mvvm.network.model.CurrentWeatherModel
import com.textsdev.cleanweatherapp_mvvm.network.model.WeatherForecastModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
    ): Response<CurrentWeatherModel>

    @GET("forecast/daily")
    suspend fun getWeatherForecast(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
    ): Response<WeatherForecastModel>
}