package com.shooter.weatherforecast.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.shooter.weatherforecast.model.WeatherData
import com.shooter.weatherforecast.model.WeatherDataProcessor
import com.shooter.weatherforecast.utils.DBHelper

class WeatherDataViewModel @JvmOverloads constructor(app: Application, val weatherDataProcessor: WeatherDataProcessor = WeatherDataProcessor()) : ObservableViewModel(app), WeatherDataProcessor.OnWeatherDataListener {

    override fun onSuccess(weatherData: List<WeatherData>) {
        weatherDataProcessor.saveWeatherData(weatherData)
//                updateOutputs(weatherData)
    }

    var lastWeatherData = WeatherData()
    var weatherDataListener: WeatherDataProcessor.OnWeatherDataListener? = null
    val db by lazy { DBHelper(app) }

    init {
        weatherDataListener = this
        weatherDataProcessor.getWeatherData(this)
    }

    fun updateOutputs(wd: WeatherData) {
        lastWeatherData = wd
        notifyChange()
    }

    fun loadSavedWeatherDataSummaries(): LiveData<List<WeatherDataSummaryItem>> {
        weatherDataProcessor.mergeLocalDataList(db.readData())
        return Transformations.map(weatherDataProcessor.loadWeatherData()) { weatherDataObjects ->
            weatherDataObjects.map {
                WeatherDataSummaryItem(it.temp,
                        it.dateCreated)
            }
        }
    }
}