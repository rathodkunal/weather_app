package com.example.weatherapplication.di

import com.example.weatherapplication.network.WeatherApi
import com.example.weatherapplication.utils.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Kunal Rathod
 * @since 20 March 2023
 * creating network dependency for making api calls
 * */
val networkModule = module {

    //provide Gson
    single<Gson> {
        GsonBuilder().create()
    }

    // Provide HttpLoggingInterceptor
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    // Provide OkHttpClient
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    // Provide Retrofit
    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .build()
    }

    // Provide WeatherApi
    single {
        get<Retrofit>().create(WeatherApi::class.java)
    }

}