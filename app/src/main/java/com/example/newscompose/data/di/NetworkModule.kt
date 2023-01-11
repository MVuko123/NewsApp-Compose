package com.example.newscompose.data.di

import android.util.Log
import com.example.newscompose.data.network.NewsService
import com.example.newscompose.data.network.NewsServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single <NewsService> { NewsServiceImpl(client = get()) }
    single {
        HttpClient(Android){
            expectSuccess = true
            install(Logging){
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("Ktor: ", message)
                    }
                }
            }
            install(ContentNegotiation){
                json(Json{
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}
