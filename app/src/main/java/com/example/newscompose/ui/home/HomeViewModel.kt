package com.example.newscompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.data.network.model.Source
import com.example.newscompose.data.repository.NewsRepository
import com.example.newscompose.model.NewsCategory
import com.example.newscompose.ui.home.mapper.HomeScreenMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val newsRepository: NewsRepository,
    val homeScreenMapper: HomeScreenMapper
) : ViewModel() {
    val newsCategory = listOf(
        NewsCategory.NEWS_EU,
        NewsCategory.NEWS_POLITICS,
        NewsCategory.NEWS_CLIMATE,
        NewsCategory.NEWS_UKRAINE,
        NewsCategory.NEWS_TECHNOLOGY,
        NewsCategory.NEWS_US_POLITICS
    )

    private val intialHomeMovieCategoryViewState =
        HomeCategoryViewState(emptyList(), emptyList())

    private val _newsViewState = MutableStateFlow(NewsCategory.NEWS_EU)
    val newsViewState = _newsViewState.flatMapLatest { category ->
        newsRepository.news(category).map { news ->
            homeScreenMapper.toHomeCategoryViewState(newsCategory, category, news)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = intialHomeMovieCategoryViewState
    )

    fun toggleSaved(source: Source?){
        viewModelScope.launch {
            newsRepository.toggleSaved(source)
        }
    }

    fun switchSeletectedCategory(categoryId: Int){
        when(categoryId){
                NewsCategory.NEWS_EU.ordinal,
                NewsCategory.NEWS_CLIMATE.ordinal,
                NewsCategory.NEWS_UKRAINE.ordinal,
                NewsCategory.NEWS_TECHNOLOGY.ordinal,
                NewsCategory.NEWS_POLITICS.ordinal,
                NewsCategory.NEWS_US_POLITICS.ordinal
                    -> {
                        _newsViewState.update { NewsCategory.values()[categoryId] }
                    }
        }
    }
}
