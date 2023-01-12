package com.example.newscompose

import android.app.Application
import android.util.Log
import com.example.newscompose.data.di.concurrencyModule
import com.example.newscompose.data.di.dataBaseModule
import com.example.newscompose.data.di.dataModule
import com.example.newscompose.data.di.networkModule
import com.example.newscompose.ui.home.di.homeModule
import com.example.newscompose.ui.newsDetails.di.newsDetailsModule
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
                concurrencyModule,
                dataBaseModule,
                dataModule,
                networkModule,
                homeModule,
                newsDetailsModule
            )
        }

        Log.d("NewsApp", "App started")
    }
}
