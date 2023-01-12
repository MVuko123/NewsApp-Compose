package com.example.newscompose.ui.newsDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.data.network.model.Source
import com.example.newscompose.data.repository.NewsRepository
import com.example.newscompose.ui.newsDetails.mapper.NewsDetailsMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NewsDetailsViewModel(
    private val newsRepository: NewsRepository,
    val newsDetailsMapper: NewsDetailsMapper,
    val source: Source
): ViewModel() {
    val newsDetailsViewState: StateFlow<NewsDetailsViewState> =
        newsRepository.newsDetails(source)
            .map { news -> newsDetailsMapper.toNewsDetailsViewState(news) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(1000L),
                NewsDetailsViewState(
                    source = source,
                    headImageUrl = "",
                    title = "",
                    date = "",
                    author = "",
                    content = "",
                    isSaved = true
                )
            )

    fun toggleSaved(source: Source?){
        viewModelScope.launch {
            newsRepository.toggleSaved(source)
        }
    }
}
