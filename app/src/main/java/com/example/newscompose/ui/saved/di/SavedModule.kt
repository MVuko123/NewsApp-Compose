package com.example.newscompose.ui.saved.di

import com.example.newscompose.ui.saved.SavedViewModel
import com.example.newscompose.ui.saved.mapper.SavedMapper
import com.example.newscompose.ui.saved.mapper.SavedMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val savedModule = module {
    viewModel {
        SavedViewModel(
            newsRepository = get(),
            savedMapper = get()
        )
    }
    single<SavedMapper> { SavedMapperImpl() }
}
