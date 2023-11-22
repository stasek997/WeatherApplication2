package com.example.weatherapplication2.repository

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.weatherapplication.WeatherApp
import com.weatherapplication.data.City
import com.weatherapplication.data.Weather
import com.weatherapplication.data.WeatherModelDTO
import com.weatherapplication.network.WeatherApiInterface
import com.weatherapplication.repository.WeatherRepositoryImpl
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.verify
import java.io.InputStream
import java.lang.reflect.Type

class WeatherRepositoryTest {
    private val apiInterface = Mockito.mock(WeatherApiInterface::class.java)
    private val assets = Mockito.mock(AssetManager::class.java)
    private val gson = Mockito.mock(Gson::class.java)
    private val type = Mockito.mock(Type::class.java)
    private val repository = WeatherRepositoryImpl(apiInterface = apiInterface, assets = assets, gson = gson, type = type)


    @Test
    fun successGetWeatherInfo() = runTest {

        val cityId = 0

        repository.getWeatherInfo(cityId)

        verify(apiInterface).getWeather(cityId)

    }



   // @Test
   // fun successGetCityList() = runTest {

        // runBlocking {

//        val inputStream = Mockito.mock(InputStream::class.java)
//
//        val cityList = arrayListOf<City>()
//
//        `when`(assets.open("city_list.json")).thenReturn(inputStream)
//
//        `when`(inputStream.available()).thenReturn(1)
//
//        `when`(
//            gson.fromJson<ArrayList<City>>(
//                anyOrNull<String>(),
//                type)).thenReturn(cityList)
//
//        `when`(inputStream.read(any())).thenReturn(-1)
//
//        repository.getCityList()
//
//        verify(inputStream).close()
//
//    }
//
}

//    override suspend fun getCityList(): MutableList<City> {
//
//        val stream = context.assets.open("city_list.json")
//
//        val size = stream.available()
//        val buffer = ByteArray(size)
//        stream.read(buffer)
//        stream.close()
//        val tContents = String(buffer)
//
//        val groupListType = object : TypeToken<ArrayList<City>>() {}.type
//        val gson = GsonBuilder().create()
//        val cityList: MutableList<City> = gson.fromJson(tContents, groupListType)
//
//        return cityList//let presenter know the city list
//
//    }
//



