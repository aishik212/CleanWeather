package com.textsdev.cleanweatherapp_mvvm.network.model


import com.google.gson.annotations.SerializedName

data class WeatherForecastModel(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int, // 7
    @SerializedName("cod")
    val cod: String, // 200
    @SerializedName("list")
    val list: List<WeatherForecastItem>,
    @SerializedName("message")
    val message: Double // 10.6724913
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

    data class WeatherForecastItem(
        @SerializedName("clouds")
        val clouds: Int, // 13
        @SerializedName("deg")
        val deg: Int, // 124
        @SerializedName("dt")
        val dt: Int, // 1712125800
        @SerializedName("feels_like")
        val feelsLike: FeelsLike,
        @SerializedName("gust")
        val gust: Double, // 10.96
        @SerializedName("humidity")
        val humidity: Int, // 27
        @SerializedName("pop")
        val pop: Int, // 0
        @SerializedName("pressure")
        val pressure: Int, // 1012
        @SerializedName("speed")
        val speed: Double, // 5.4
        @SerializedName("sunrise")
        val sunrise: Int, // 1712105054
        @SerializedName("sunset")
        val sunset: Int, // 1712149263
        @SerializedName("temp")
        val temp: Temp,
        @SerializedName("weather")
        val weather: List<Weather>
    ) {
        data class FeelsLike(
            @SerializedName("day")
            val day: Double, // 305.15
            @SerializedName("eve")
            val eve: Double, // 305.88
            @SerializedName("morn")
            val morn: Double, // 295.64
            @SerializedName("night")
            val night: Double // 299.92
        )

        data class Temp(
            @SerializedName("day")
            val day: Double, // 306.49
            @SerializedName("eve")
            val eve: Double, // 307.66
            @SerializedName("max")
            val max: Double, // 307.7
            @SerializedName("min")
            val min: Double, // 295.43
            @SerializedName("morn")
            val morn: Double, // 295.73
            @SerializedName("night")
            val night: Double // 300.63
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
    }
}