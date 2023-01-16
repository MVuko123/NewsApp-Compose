package com.example.newscompose.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.data.repository.NewsRepository
import com.example.newscompose.ui.search.mapper.SearchScreenMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val newsRepository: NewsRepository,
    val searchScreenMapper: SearchScreenMapper,
) : ViewModel() {
    private val initalSearchedCategoryViewState =
        SearchCategoryViewState(emptyList())

    private val _topic = MutableStateFlow("")
    val topic = mutableStateOf("")

    var _searched =
        _topic.flatMapLatest { topic ->
            newsRepository.searchNews(topic).map { news ->
                searchScreenMapper.toSearchCategoryViewState(news = news)
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(1000L),
            initalSearchedCategoryViewState
        )

    var searched: StateFlow<SearchCategoryViewState> = _searched

    fun setTopic(topic: String) {
        this._topic.value = topic

        this.topic.value = topic
    }

    fun toggleSaved(url: String) {
        viewModelScope.launch {
            newsRepository.toggleSaved(url)
        }
    }
}
