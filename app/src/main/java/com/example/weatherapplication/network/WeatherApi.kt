package com.example.weatherapplication.network

import com.example.weatherapplication.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun fetchWeatherInformation(@Query("q") cityName: String,@Query("appid") appId : String) : WeatherResponse


    @GET("data/2.5/weather")
    suspend fun fetchWeatherInformationWithLatLong(@Query("lat") lat: String,@Query("lon") long: String,@Query("appid") appId : String) : WeatherResponse

}