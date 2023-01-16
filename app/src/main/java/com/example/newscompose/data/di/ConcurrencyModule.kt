package com.example.newscompose.data.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val concurrencyModule = module {
    single(named("dispatcherIO")) {
        Dispatchers.IO
    }
}
