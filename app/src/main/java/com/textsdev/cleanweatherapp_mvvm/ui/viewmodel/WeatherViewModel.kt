package com.textsdev.cleanweatherapp_mvvm.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.textsdev.cleanweatherapp_mvvm.data.model.CurrentWeatherOfflineModel
import com.textsdev.cleanweatherapp_mvvm.data.model.WeatherForecastOfflineModel
import com.textsdev.cleanweatherapp_mvvm.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> get() = _error

    init {
        getCurrentWeather("Bengaluru")
        getWeatherForecast("Bengaluru")
    }

    fun getCurrentWeather(cityName: String) {
        startLoading()
        viewModelScope.launch {
            //Added Delay to show loader
            delay(500)
            try {
                val weatherData = withContext(Dispatchers.IO) {
                    weatherRepository.getCurrentWeather(cityName)
                }
                _weather.value = weatherData
                stopLoading()
            } catch (e: Exception) {
                stopLoading()
                _error.value = e
            }
        }
    }

    fun getWeatherForecast(cityName: String) {
        startLoading()
        viewModelScope.launch {
            //Added Delay to show loader
            delay(500)
            try {
                val weatherData = withContext(Dispatchers.IO) {
                    weatherRepository.getWeatherForecast(cityName)
                }
                _weatherForecast.value = weatherData
                stopLoading()
            } catch (e: Exception) {
                stopLoading()
                _error.value = e
            }
        }
    }

    fun startLoading() {
        _isLoading.value = true
    }

    fun stopLoading() {
        _isLoading.value = false
    }
}