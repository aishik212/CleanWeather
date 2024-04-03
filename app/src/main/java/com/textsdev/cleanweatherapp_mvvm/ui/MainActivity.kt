package com.textsdev.cleanweatherapp_mvvm.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.textsdev.cleanweatherapp_mvvm.adapter.ForecastAdapter
import com.textsdev.cleanweatherapp_mvvm.data.model.CurrentWeatherOfflineModel
import com.textsdev.cleanweatherapp_mvvm.databinding.ActivityMainBinding
import com.textsdev.cleanweatherapp_mvvm.ui.viewmodel.WeatherViewModel
import com.textsdev.cleanweatherapp_mvvm.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val forecastAdapter = ForecastAdapter(listOf())
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initViews()
        addObservers()
    }


    private fun initViews() {
        binding.forecastRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.forecastRecyclerView.adapter = forecastAdapter
    }

    private fun addObservers() {
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                // Show loading indicator (e.g., progress bar)
                binding.loadingProgressIndicator.visibility = VISIBLE
            } else {
                // Hide loading indicator
                binding.loadingProgressIndicator.visibility = GONE
            }
        }

        viewModel.error.observe(this) { error ->
            //Show snackbar if error not null
            if (error == null) {
                binding.mainLayout.visibility = VISIBLE
            } else {
                binding.mainLayout.visibility = GONE
                //Could've showed separate message for each error, didn't add it due to time constraint
                showSnackBar("Something went wrong", binding.root) {
                    viewModel.fetchWeather("Bengaluru")
                }
            }
        }

        viewModel.weather.observe(this) { weatherOfflineModel: CurrentWeatherOfflineModel? ->
            try {
                weatherOfflineModel?.let {
                    binding.cityNameTv.text = weatherOfflineModel.cityName
                    "${weatherOfflineModel.temperature.roundToInt()}°".also {
                        binding.currentTempTv.text = it
                    }
                } ?: run {
                    showSnackBar("Unable to show current weather", binding.root) {
                        viewModel.fetchWeather("Bengaluru")
                    }
                }
            } catch (e: Exception) {
                showSnackBar("Unable to show current weather", binding.root) {
                    viewModel.fetchWeather("Bengaluru")
                }

            }

        }

        viewModel.weatherForecast.observe(this) { weatherForecastOfflineModelList ->
            forecastAdapter.setData(weatherForecastOfflineModelList)
        }
    }
}