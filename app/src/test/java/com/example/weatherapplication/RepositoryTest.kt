package com.example.weatherapplication

import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.viewmodel.WeatherViewModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.math.sin

class RepositoryTest : KoinTest {

    val repository by inject<WeatherRepository>()
    val modules = module {
        single { WeatherRepository(get()) }
        viewModel { WeatherViewModel(get()) }
    }

    @Before
    fun before() {
        startKoin{modules(modules)}
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun testGetWeatherSuccess() {
        val address = "Paris"
        runBlocking {
            repository.getWeatherInformation(address)
            val test = repository.getWeatherInformation(address)
        }
    }

}