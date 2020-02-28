package com.shooter.weatherforecast.network.Response.modal

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class City {

    @SerializedName("id")
    @Expose
    val id: Int = 0
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("coord")
    @Expose
    val coord: Coord? = null
    @SerializedName("country")
    @Expose
    val country: String? = null
    @SerializedName("population")
    @Expose
    val population: Int = 0
    @SerializedName("timezone")
    @Expose
    val timezone: Int = 0
    @SerializedName("sunrise")
    @Expose
    val sunrise: Int = 0
    @SerializedName("sunset")
    @Expose
    val sunset: Int = 0
}
