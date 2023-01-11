package com.example.newscompose.data.di

import com.example.newscompose.data.repository.NewsRepository
import com.example.newscompose.data.repository.NewsRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single <NewsRepository>{
        NewsRepositoryImpl(get(), get(), get(named("dispatcherIO")))
    }
}
