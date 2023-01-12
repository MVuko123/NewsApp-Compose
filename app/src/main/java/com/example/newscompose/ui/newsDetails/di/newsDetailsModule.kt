package com.example.newscompose.ui.newsDetails.di

import com.example.newscompose.ui.newsDetails.NewsDetailsViewModel
import com.example.newscompose.ui.newsDetails.mapper.NewsDetailsMapper
import com.example.newscompose.ui.newsDetails.mapper.NewsDetailsMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsDetailsModule = module {
    viewModel{
        NewsDetailsViewModel(
            newsRepository = get(),
            newsDetailsMapper = get(),
            source = get()
        )
    }
    single <NewsDetailsMapper> { NewsDetailsMapperImpl() }
}
