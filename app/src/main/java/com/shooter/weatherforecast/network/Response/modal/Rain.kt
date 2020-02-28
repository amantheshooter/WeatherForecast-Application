package com.shooter.weatherforecast.network.Response.modal

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rain {

    @SerializedName("3h")
    @Expose
    private val _3h: Double = 0.toDouble()
}