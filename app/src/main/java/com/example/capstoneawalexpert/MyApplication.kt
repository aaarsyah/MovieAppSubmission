package com.example.capstoneawalexpert

import android.app.Application
import com.example.capstoneawalexpert.core.di.databaseModule
import com.example.capstoneawalexpert.core.di.networkModule
import com.example.capstoneawalexpert.core.di.repositoryModule
import com.example.capstoneawalexpert.di.useCaseModule
import com.example.capstoneawalexpert.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}