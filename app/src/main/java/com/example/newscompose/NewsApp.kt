package com.example.newscompose

import android.app.Application
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.INFO)
            androidContext(this@NewsApp)
            modules(

            )
        }

        Log.d("NewsApp", "App started")
    }
}
