package com.example.weatherapplication2.viewmodels

import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapplication2.utills.MainCoroutineRule
import com.example.weatherapplication2.utills.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import com.weatherapplication.data.City
import com.weatherapplication.data.Clouds
import com.weatherapplication.data.Coord
import com.weatherapplication.data.Main
import com.weatherapplication.data.Sys
import com.weatherapplication.data.Weather
import com.weatherapplication.data.WeatherData
import com.weatherapplication.data.WeatherModelDTO
import com.weatherapplication.data.Wind
import com.weatherapplication.repository.WeatherPref
import com.weatherapplication.repository.WeatherRepository
import com.weatherapplication.viewmodels.AddScreenViewModel
import com.weatherapplication.viewmodels.MainActivityViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.robolectric.Shadows


//@RunWith(RobolectricGradleTestRunner.class)

class MainActivityViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val weatherRepository = Mockito.mock(WeatherRepository::class.java)
    private val weatherPref = Mockito.mock(WeatherPref::class.java)

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
    private val viewModel = MainActivityViewModel(
        weatherRepository, weatherPref
    )


    @Test

    fun succeessGetWeatherInfo() = runTest {

        val cityId = 0

        `when`(weatherRepository.getWeatherInfo(cityId)).thenReturn(weatherModelDTO)

        viewModel.getWeatherInfo(cityId)

        assertThat(viewModel.weatherInfoLiveData.getOrAwaitValue()).isEqualTo(weatherData)
    }


    @Test

    fun failGetWeatherInfo() = runTest {

        val cityId = 0

        `when`(weatherRepository.getWeatherInfo(cityId)).then { throw Exception("123") }
        viewModel.getWeatherInfo(cityId)
        assertThat(viewModel.weatherInfoFailureLiveData.getOrAwaitValue()).isEqualTo("123")

    }

}

val weatherModelDTO = WeatherModelDTO(
    base = "stations",
    clouds = Clouds(all = 0),
    cod = 200,
    coord = Coord(lat = 24.4667, lon = 54.3667),
    dt = 1700663355,
    id = 292968,
    main = Main(
        feelsLike = 303.14, humidity = 59, pressure = 1017,
        temp = 301.59, tempMax = 302.01, tempMin = 301.16
    ),
    name = "Abu Dhabi",
    sys = Sys(country = "AE", id = 2032466, sunrise = 1700620977, sunset = 1700660066, type = 2),
    timezone = 14400,
    visibility = 10000,
    weather = listOf(Weather(description = "clear sky", icon = "01n", id = 800, main = "Clear")),
    wind = Wind(deg = 340, speed = 3.6)
)

val weatherData = WeatherData(
    dateTime = "22 Nov, 2023 - 05:29 PM",
    temperature = "28",
    cityAndCountry = "Abu Dhabi, AE",
    weatherConditionIconUrl = "http://openweathermap.org/img/w/01n.png",
    weatherConditionIconDescription = "clear sky",
    humidity = "59%",
    pressure = "1017 mBar",
    visibility = "10.0 KM",
    sunrise = "05:42 AM",
    sunset = "04:34 PM"
)
