package com.example.weatherapplication

import android.app.Application
import com.example.weatherapplication.di.networkModule
import com.example.weatherapplication.di.repositoryModule
import com.example.weatherapplication.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    private val appModules = listOf(
        repositoryModule,
        networkModule,
        vmModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(appModules)
        }
    }
}