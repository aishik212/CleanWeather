package com.textsdev.cleanweatherapp_mvvm.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.textsdev.cleanweatherapp_mvvm.data.model.CurrentWeatherOfflineModel
import com.textsdev.cleanweatherapp_mvvm.data.model.WeatherForecastOfflineModel
import com.textsdev.cleanweatherapp_mvvm.data.repository.WeatherRepository
import com.textsdev.cleanweatherapp_mvvm.data.repository.WeatherResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private val _weather = MutableLiveData<CurrentWeatherOfflineModel>()
    val weather: LiveData<CurrentWeatherOfflineModel> get() = _weather

    private val _weatherForecast = MutableLiveData<List<WeatherForecastOfflineModel>>()
    val weatherForecast: MutableLiveData<List<WeatherForecastOfflineModel>> get() = _weatherForecast

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<Throwable?>()
    val error: LiveData<Throwable?> get() = _error

    init {
        fetchWeather("Bengaluru")
    }

    fun fetchWeather(cityName: String) {
        startLoading()
        viewModelScope.launch {
            // Added delay to show loader
            delay(500)
            try {
                clearError()
                when (val currentWeather = weatherRepository.getCurrentWeather(cityName)) {
                    is WeatherResult.Success -> {
                        val weatherForecast = weatherRepository.getWeatherForecast(cityName)
                        when (weatherForecast) {
                            is WeatherResult.Success -> {
                                // Handle successful result
                                // If All success then show data
                                _weather.value = currentWeather.data
                                _weatherForecast.value = weatherForecast.data
                            }

                            is WeatherResult.Error -> {
                                _error.value = Throwable(weatherForecast.message)
                                // Handle error
                            }
                        }

                    }

                    is WeatherResult.Error -> {
                        _error.value = Throwable(currentWeather.message)
                        // Handle error
                    }
                }
            } catch (e: Exception) {
                _error.value = e
            } finally {
                stopLoading()
            }
        }
    }

    fun startLoading() {
        _isLoading.value = true
    }

    fun stopLoading() {
        _isLoading.value = false
    }

    fun clearError() {
        _error.value = null
    }
}