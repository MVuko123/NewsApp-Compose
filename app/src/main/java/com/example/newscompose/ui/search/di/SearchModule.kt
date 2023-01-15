package com.example.newscompose.ui.search.di

import com.example.newscompose.ui.search.SearchViewModel
import com.example.newscompose.ui.search.mapper.SearchScreenMapper
import com.example.newscompose.ui.search.mapper.SearchScreenMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel{
        SearchViewModel(
            newsRepository = get(),
            searchScreenMapper = get()
        )
    }
    single<SearchScreenMapper>{ SearchScreenMapperImpl() }
}
