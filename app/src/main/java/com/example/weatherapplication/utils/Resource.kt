package com.example.weatherapplication.utils


/**
 * @author Kunal Rathod
 * @since 20 March 2023
 * this class hold the callback of API response & its generic
 * */
sealed class Resource<T>(val data : T? = null, val message : String? = null){
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message : String, data: T? = null) : Resource<T>(data,message)
}
