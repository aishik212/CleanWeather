package com.textsdev.cleanweatherapp_mvvm.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.textsdev.cleanweatherapp_mvvm.databinding.ActivityMainBinding
import com.textsdev.cleanweatherapp_mvvm.ui.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        addObservers()
    }

    private fun addObservers() {
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                // Show loading indicator (e.g., progress bar)
                binding.loadingProgressIndicator.visibility = View.VISIBLE
            } else {
                // Hide loading indicator
                binding.loadingProgressIndicator.visibility = View.GONE
            }
        }

        viewModel.error.observe(this) {
            //Show snackbar if error not null
            it?.let {
                //Could've showed separate message for each error, didn't add it due to time constraint
                Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_LONG)
                    .setAction("RETRY") {
                        viewModel.fetchWeather("Bengaluru")
                    }.show()
                viewModel.clearError()
            }
        }
    }
}