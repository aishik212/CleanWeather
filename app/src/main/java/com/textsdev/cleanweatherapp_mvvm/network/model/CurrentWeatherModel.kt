package com.textsdev.cleanweatherapp_mvvm.network.model


import com.google.gson.annotations.SerializedName

data class CurrentWeatherModel(
    @SerializedName("base")
    val base: String, // stations
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("cod")
    val cod: Int, // 200
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("dt")
    val dt: Int, // 1712124182
    @SerializedName("id")
    val id: Int, // 1277333
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String, // Bengaluru
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Int, // 19800
    @SerializedName("visibility")
    val visibility: Int, // 10000
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
) {
    data class Clouds(
        @SerializedName("all")
        val all: Int // 20
    )

    data class Coord(
        @SerializedName("lat")
        val lat: Double, // 12.9762
        @SerializedName("lon")
        val lon: Double // 77.6033
    )

    data class Main(
        @SerializedName("feels_like")
        val feelsLike: Double, // 305.73
        @SerializedName("humidity")
        val humidity: Int, // 46
        @SerializedName("pressure")
        val pressure: Int, // 1017
        @SerializedName("temp")
        val temp: Double, // 304.61
        @SerializedName("temp_max")
        val tempMax: Double, // 306.77
        @SerializedName("temp_min")
        val tempMin: Double // 302.95
    )

    data class Sys(
        @SerializedName("country")
        val country: String, // IN
        @SerializedName("id")
        val id: Int, // 2017753
        @SerializedName("sunrise")
        val sunrise: Int, // 1712105054
        @SerializedName("sunset")
        val sunset: Int, // 1712149263
        @SerializedName("type")
        val type: Int // 2
    )

    data class Weather(
        @SerializedName("description")
        val description: String, // few clouds
        @SerializedName("icon")
        val icon: String, // 02d
        @SerializedName("id")
        val id: Int, // 801
        @SerializedName("main")
        val main: String // Clouds
    )

    data class Wind(
        @SerializedName("deg")
        val deg: Int, // 260
        @SerializedName("speed")
        val speed: Double // 1.54
    )
}