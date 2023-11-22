package com.weatherapplication.repository


import android.content.res.AssetManager
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken

import com.weatherapplication.data.City
import com.weatherapplication.data.WeatherModelDTO
import com.weatherapplication.network.WeatherApiInterface

import java.lang.reflect.Type


class WeatherRepositoryImpl constructor (
    private val assets: AssetManager,
    private val apiInterface: WeatherApiInterface,
    private val gson: Gson,
    private val type: Type
): WeatherRepository {

    override suspend fun getCityList(): MutableList<City> {

            val stream = assets.open("city_list.json")

            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            val tContents = String(buffer)

            val cityList: MutableList<City> = gson.fromJson(tContents, type)

            return cityList//let presenter know the city list

    }

    override suspend fun getWeatherInfo(cityId: Int): WeatherModelDTO = apiInterface.getWeather(cityId)


}







