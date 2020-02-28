package com.shooter.weatherforecast.network.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.shooter.weatherforecast.network.Response.modal.City
import com.shooter.weatherforecast.network.Response.modal.WeatherList

class ForecastResponse {

    @SerializedName("cod")
    @Expose
    val cod: String? = null
    @SerializedName("message")
    @Expose
    val message: Int = 0
    @SerializedName("cnt")
    @Expose
    val cnt: Int = 0
    @SerializedName("list")
    @Expose
    val weatherList: List<WeatherList>? = null
    @SerializedName("city")
    @Expose
    val city: City? = null
}
