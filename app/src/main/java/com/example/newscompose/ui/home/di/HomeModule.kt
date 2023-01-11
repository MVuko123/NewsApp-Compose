package com.example.newscompose.ui.home.di

import com.example.newscompose.ui.home.HomeViewModel
import com.example.newscompose.ui.home.mapper.HomeScreenMapper
import com.example.newscompose.ui.home.mapper.HomeScreenMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(
            newsRepository = get(),
            homeScreenMapper = get()
        )
    }
    single<HomeScreenMapper>{ HomeScreenMapperImpl() }
}
