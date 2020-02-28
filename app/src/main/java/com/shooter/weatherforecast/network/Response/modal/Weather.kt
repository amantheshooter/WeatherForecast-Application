package com.shooter.weatherforecast.network.Response.modal

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather {

    @SerializedName("id")
    @Expose
     val id: Int = 0
    @SerializedName("main")
    @Expose
     val main: String? = null
    @SerializedName("description")
    @Expose
     val description: String? = null
    @SerializedName("icon")
    @Expose
     val icon: String? = null
}