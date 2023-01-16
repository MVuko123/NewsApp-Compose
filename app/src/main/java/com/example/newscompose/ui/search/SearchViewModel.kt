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

     var _searched =
        if(_topic.value == "" || topic.value == ""){
            newsRepository.searchNews("").map { news ->
                searchScreenMapper.toSearchCategoryViewState(news = news)
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(1000L),
                initalSearchedCategoryViewState
            )
        }
        else {
            _topic.flatMapLatest { topic ->
                newsRepository.searchNews(topic).map { news ->
                    searchScreenMapper.toSearchCategoryViewState(news = news)
                }
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(1000L),
                initalSearchedCategoryViewState
            )
        }

    var searched: StateFlow<SearchCategoryViewState> = _searched

    fun setTopic(topic: String){
        this._topic.value = topic

        this.topic.value = topic
    }

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

    fun toggleSaved(url: String){
        viewModelScope.launch {
            newsRepository.toggleSaved(url)
        }
    }
}
