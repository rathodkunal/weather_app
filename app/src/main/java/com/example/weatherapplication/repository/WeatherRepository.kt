package com.example.weatherapplication.repository

import android.util.Log
import com.example.weatherapplication.utils.Resource
import com.example.weatherapplication.models.WeatherResponse
import com.example.weatherapplication.network.WeatherApi
import com.example.weatherapplication.utils.Constants.APP_ID

/**
 * @author Kunal Rathod
 * @since 20 March 2023
 * @param get weatherApi as param using koin DI for making api call
 * */

class WeatherRepository(private val weatherApi: WeatherApi) {

    suspend fun getWeatherInformation(cityName : String) : Resource<WeatherResponse> {
        val response = try {
            weatherApi.fetchWeatherInformation(cityName,APP_ID)
        }catch (e : Exception){
            return Resource.Error("Error Occured");
        }
        return Resource.Success(response)
    }

    suspend fun getWeatherInformationWithLatLong(lat : String,lon :String) : Resource<WeatherResponse> {
        val response = try {
            weatherApi.fetchWeatherInformationWithLatLong(lat,lon,APP_ID)
        }catch (e : Exception){
            return Resource.Error("Error Occured");
        }
        return Resource.Success(response)
    }
}