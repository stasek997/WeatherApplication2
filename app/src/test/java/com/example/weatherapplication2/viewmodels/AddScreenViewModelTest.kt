package com.example.weatherapplication2.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapplication2.utills.MainCoroutineRule
import com.example.weatherapplication2.utills.getOrAwaitValue
import com.weatherapplication.data.City
import com.weatherapplication.repository.WeatherPref
import com.weatherapplication.repository.WeatherRepository
import com.weatherapplication.viewmodels.AddScreenViewModel
import junit.framework.TestCase.fail
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any

class AddScreenViewModelTest {

    @get:Rule    //Правило для того, чтобы не было ошибок для работы лайв даты в тестах
    var instantExecutorRule = InstantTaskExecutorRule()

    private val weatherRepository = Mockito.mock(WeatherRepository::class.java)
    private val weatherPref = Mockito.mock(WeatherPref::class.java)

    @get:Rule   //Правило для того, чтобы основной поток использовал по дефолту тестовый диспатчер анконфайн
    val mainCoroutineRule = MainCoroutineRule()
    private val viewModel = AddScreenViewModel(
        weatherRepository, weatherPref
    )

    @Test

    fun succeessGetCityList() = runTest {

        val cityList = mutableListOf<City>()

        `when`(weatherRepository.getCityList()).thenReturn(cityList)

        viewModel.getCityList()

        assert(viewModel.cityListLiveData.getOrAwaitValue() == cityList)
    }


    @Test

    fun failGetCityList() = runTest {


        `when`(weatherRepository.getCityList()).then { throw Exception("") }
        viewModel.getCityList()

        assert(viewModel.cityListFailureLiveData.getOrAwaitValue() == "")



    }

}