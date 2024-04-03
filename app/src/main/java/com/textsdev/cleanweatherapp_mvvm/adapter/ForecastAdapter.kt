package com.textsdev.cleanweatherapp_mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.textsdev.cleanweatherapp_mvvm.data.model.WeatherForecastOfflineModel
import com.textsdev.cleanweatherapp_mvvm.databinding.WeatherForecastItemsLayoutBinding
import com.textsdev.cleanweatherapp_mvvm.utils.toDay

class ForecastAdapter(private var forecastList: List<WeatherForecastOfflineModel>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = WeatherForecastItemsLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = forecastList[position]
        holder.bind(forecast)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    fun setData(weatherForecastOfflineModelList: List<WeatherForecastOfflineModel>) {
        forecastList = weatherForecastOfflineModelList
        notifyDataSetChanged()
    }

    inner class ForecastViewHolder(private val binding: WeatherForecastItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(forecast: WeatherForecastOfflineModel) {
            binding.dayTv.text = forecast.date.toDay()
            "${forecast.temp} C".also { binding.tempTv.text = it }
        }
    }
}