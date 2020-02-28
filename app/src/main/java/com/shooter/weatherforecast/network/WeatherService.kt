package com.shooter.weatherforecast.network

import com.shooter.weatherforecast.network.Response.ForecastResponse
import com.shooter.weatherforecast.network.Response.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by burakakgun on 14.08.2018.
 */
interface WeatherService {

    @GET("/data/2.5/weather")
    fun getWeather(@Query("zip") id: String, @Query("appid") appId: String, @Query("units") units: String): Call<WeatherResponse>

    @GET("/data/2.5/forecast")
    fun getWeatherForecast(@Query("q") id: String, @Query("appid") appId: String, @Query("units") units: String, @Query("ent") ent: Int): Call<ForecastResponse>
}