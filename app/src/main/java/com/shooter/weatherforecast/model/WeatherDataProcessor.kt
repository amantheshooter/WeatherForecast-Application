package com.shooter.weatherforecast.model

import android.arch.lifecycle.LiveData
import android.util.Log
import android.widget.Toast
import com.shooter.weatherforecast.network.NetworkRepository
import com.shooter.weatherforecast.network.Response.ForecastResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class WeatherDataProcessor(val repository: WeatherDataRepository = WeatherDataRepository(), val networkRepository: NetworkRepository = NetworkRepository()) {

    interface OnWeatherDataListener {
        fun onSuccess(weatherData: List<WeatherData>)
    }

    fun saveWeatherData(tc: List<WeatherData>) {
        repository.saveWeatherData(tc)
    }

    fun clearWeatherData() {
        repository.clearWeatherData()
    }

    fun mergeLocalDataList(dataList: List<WeatherData>) {
        repository.mergeLocalDataList(dataList)
    }

    fun loadWeatherDataByDt(createDate: Date): WeatherData? {
        return repository.loadWeatherDataByDt(createDate)
    }

    fun loadWeatherData(): LiveData<List<WeatherData>> {
        return repository.loadWeatherData()
    }

    fun getWeatherData(onWeatherDataListener: OnWeatherDataListener) {
        val call = networkRepository.weatherService.getWeatherForecast(networkRepository.id, networkRepository.appId, networkRepository.units, 56)
        call.enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(call: Call<ForecastResponse>?, response: Response<ForecastResponse>?) {
                if (response != null && response.isSuccessful) {
                    val weatherItem: ForecastResponse? = response.body()

                    val weatherDataList = ArrayList<WeatherData>(weatherItem?.weatherList!!.size)
                    for (i in 0..weatherItem.weatherList.size - 1) {

                        val date: List<String> = weatherItem.weatherList.get(i).dtTxt!!.split(" ")
                        if (i == 0)
                            weatherDataList.add(WeatherData(weatherItem.city?.name, weatherItem.city?.country, weatherItem.weatherList.get(i).weather?.get(0)?.description, weatherItem.weatherList.get(i).main?.temp, date.get(0)))
                        else{
                            if (!weatherDataList.get(weatherDataList.size - 1).dt!!.contains(date.get(0)))
                                weatherDataList.add(WeatherData(weatherItem.city?.name, weatherItem.city?.country, weatherItem.weatherList.get(i).weather?.get(0)?.description, weatherItem.weatherList.get(i).main?.temp, date.get(0)))
                        }

                    }

                    Log.d("size", weatherDataList.size.toString())
                    onWeatherDataListener.onSuccess(weatherDataList)
                } else {
                    onFailure(call, Throwable("Bad Response"))
                }
            }

            override fun onFailure(call: Call<ForecastResponse>?, t: Throwable?) {
                Log.e("Response Failure", t?.message, t)
            }
        })
    }


}