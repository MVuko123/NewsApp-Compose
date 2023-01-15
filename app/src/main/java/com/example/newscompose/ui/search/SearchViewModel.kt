package com.example.newscompose.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.data.network.model.Source
import com.example.newscompose.data.repository.NewsRepository
import com.example.newscompose.ui.search.mapper.SearchScreenMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SearchViewModel(
    private val newsRepository: NewsRepository,
    val searchScreenMapper: SearchScreenMapper,
) : ViewModel(){


    private val initalSearchedCategoryViewState =
        SearchCategoryViewState(emptyList())
    /*
    private val _searchViewState = MutableStateFlow(SearchCategoryViewState())
    val searchViewState = _searchViewState.flatMapLatest { topic ->
        newsRepository.searchNews(topic.toString()).map { news ->
            searchScreenMapper.toSearchCategoryViewState(news)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = initalSearchedCategoryViewState
    ) */

    private val _topic = MutableStateFlow("")
    val topic = mutableStateOf("")

    private var _searched = _topic.flatMapLatest { topic ->
        newsRepository.searchNews(topic).map { news ->
            searchScreenMapper.toSearchCategoryViewState(news = news)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(1000L),
        SearchCategoryViewState(emptyList())
    )

    var searched: StateFlow<SearchCategoryViewState> = _searched

    /*
    val searchViewState: StateFlow<SearchCategoryViewState> =
        newsRepository.searchNews(topic)
            .map { news -> searchScreenMapper.toSearchCategoryViewState(news) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1000L),
                initialValue =  initalSearchedCategoryViewState
            )

     */

    fun toggleSaved(source: Source?){
        viewModelScope.launch {
            newsRepository.toggleSaved(source)
        }
    }
}
