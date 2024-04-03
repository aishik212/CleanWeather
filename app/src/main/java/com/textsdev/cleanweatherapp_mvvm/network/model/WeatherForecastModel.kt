package com.textsdev.cleanweatherapp_mvvm.network.model


import com.google.gson.annotations.SerializedName

data class WeatherForecastModel(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int, // 40
    @SerializedName("cod")
    val cod: String, // 200
    @SerializedName("list")
    val list: List<WeatherForecastItems>,
    @SerializedName("message")
    val message: Int // 0
) {
    data class City(
        @SerializedName("coord")
        val coord: Coord,
        @SerializedName("country")
        val country: String, // IN
        @SerializedName("id")
        val id: Int, // 1277333
        @SerializedName("name")
        val name: String, // Bengaluru
        @SerializedName("population")
        val population: Int, // 5104047
        @SerializedName("sunrise")
        val sunrise: Int, // 1712105054
        @SerializedName("sunset")
        val sunset: Int, // 1712149263
        @SerializedName("timezone")
        val timezone: Int // 19800
    ) {
        data class Coord(
            @SerializedName("lat")
            val lat: Double, // 12.9762
            @SerializedName("lon")
            val lon: Double // 77.6033
        )
    }

    data class WeatherForecastItems(
        @SerializedName("clouds")
        val clouds: Clouds,
        @SerializedName("dt")
        val dt: Int, // 1712134800
        @SerializedName("dt_txt")
        val dtTxt: String, // 2024-04-03 09:00:00
        @SerializedName("main")
        val main: Main,
        @SerializedName("pop")
        val pop: Int, // 0
        @SerializedName("sys")
        val sys: Sys,
        @SerializedName("visibility")
        val visibility: Int, // 10000
        @SerializedName("weather")
        val weather: List<Weather>,
        @SerializedName("wind")
        val wind: Wind
    ) {
        data class Clouds(
            @SerializedName("all")
            val all: Int // 8
        )

        data class Main(
            @SerializedName("feels_like")
            val feelsLike: Double, // 305.72
            @SerializedName("grnd_level")
            val grndLevel: Int, // 911
            @SerializedName("humidity")
            val humidity: Int, // 35
            @SerializedName("pressure")
            val pressure: Int, // 1014
            @SerializedName("sea_level")
            val seaLevel: Int, // 1014
            @SerializedName("temp")
            val temp: Double, // 305.98
            @SerializedName("temp_kf")
            val tempKf: Double, // -3.03
            @SerializedName("temp_max")
            val tempMax: Double, // 309.01
            @SerializedName("temp_min")
            val tempMin: Double // 305.98
        )

        data class Sys(
            @SerializedName("pod")
            val pod: String // d
        )

        data class Weather(
            @SerializedName("description")
            val description: String, // clear sky
            @SerializedName("icon")
            val icon: String, // 01d
            @SerializedName("id")
            val id: Int, // 800
            @SerializedName("main")
            val main: String // Clear
        )

        data class Wind(
            @SerializedName("deg")
            val deg: Int, // 101
            @SerializedName("gust")
            val gust: Double, // 5.14
            @SerializedName("speed")
            val speed: Double // 4.8
        )
    }
}