package com.example.weatherapplication.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.models.WeatherResponse
import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
/**
 * @author Kunal Rathod
 * @since 20 March 2023
 * viewModel class that hold weatherRepository instance to make api call
 * */
class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    val weatherResponse = mutableStateOf<WeatherResponse?>(null)
    var isLoading = mutableStateOf(false)

    /**
     * get weather data according to city name
     * if location permission is denied then this function will get called
     * */
    fun fetchWeatherInfo(cityName : String){
        viewModelScope.launch {
            isLoading.value = true
            val result = weatherRepository.getWeatherInformation(cityName)
            when(result){
                is Resource.Success -> {
                    weatherResponse.value = result.data
                    isLoading.value = false
                }
                is Resource.Error -> {
                    isLoading.value = false
                }
            }
        }
    }

    /**
     * get weather data according to current lat long
     * if location permission is allowed then this function will get called
     * */
    fun fetchWeatherInfoUsingLatLong(lat : String,long : String){
        viewModelScope.launch {
            isLoading.value = true
            val result = weatherRepository.getWeatherInformationWithLatLong(lat,long)
            when(result){
                is Resource.Success -> {
                    weatherResponse.value = result.data
                    isLoading.value = false
                }
                is Resource.Error -> {
                    isLoading.value = false
                }
            }
        }
    }
}