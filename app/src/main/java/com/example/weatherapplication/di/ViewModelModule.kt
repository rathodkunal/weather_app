package com.example.weatherapplication.di

import com.example.weatherapplication.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Kunal Rathod
 * @since 20 March 2023
 * this class hold viewModel dependency
 * */
val vmModule = module {

    // Provide MainActivityViewModel
    viewModel { WeatherViewModel(get()) }

}