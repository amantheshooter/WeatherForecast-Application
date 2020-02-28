package com.shooter.weatherforecast.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.*
import kotlin.collections.ArrayList

class WeatherDataRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var repository: WeatherDataRepository

    @Before
    fun setup() {
        repository = WeatherDataRepository()
    }

    @Test
    fun testSaveWeatherData() {
        val weatherData = WeatherData(description = "Cloudy",
                temp = 100.0, name = "Ankara",
                country = "TR", dt = "10000000", dateCreated = Date())

        var data = ArrayList<WeatherData>(5)
        data.add(weatherData)

        repository.saveWeatherData(data)
        assertEquals(weatherData, repository.loadWeatherDataByDt(weatherData.dateCreated))
    }

    @Test
    fun testClearWeatherData() {
        repository.clearWeatherData()
        assertEquals(0, repository.loadWeatherData().value?.size)
    }

    @Test
    fun testLoadSavedWeatherData() {
        val weatherData1 = WeatherData(description = "Cloudy",
                temp = 100.0, name = "NOIDA",
                country = "IN", dt = "10000000", dateCreated = Date())
        val weatherData2 = WeatherData(description = "Sun",
                temp = 125.0, name = "DELHI",
                country = "IN", dt = "10000000", dateCreated = Date(1231231231))

        repository.saveWeatherData(listOf(weatherData1))
        repository.saveWeatherData(listOf(weatherData2))

        repository.loadWeatherData().observeForever { weatherData ->
            assertEquals(2, weatherData?.size)
        }
    }
}