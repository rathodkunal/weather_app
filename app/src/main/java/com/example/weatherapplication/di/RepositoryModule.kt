package com.example.weatherapplication.di

import com.example.weatherapplication.repository.WeatherRepository
import org.koin.dsl.module

/**
 * @author Kunal Rathod
 * @since 20 March 2023
 * this class hold repository dependency
 * */

val repositoryModule =  module {

    //providing repository
    single {
        WeatherRepository(get())
    }
}