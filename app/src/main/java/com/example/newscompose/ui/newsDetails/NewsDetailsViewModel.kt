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
    val id: Long?
): ViewModel() {
    val newsDetailsViewState: StateFlow<NewsDetailsViewState> =
        newsRepository.newsDetails(id = id)
            .map { news -> newsDetailsMapper.toNewsDetailsViewState(news) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(1000L),
                NewsDetailsViewState(
                    id = id,
                    source = Source("",""),
                    headImageUrl = "",
                    title = "",
                    date = "",
                    author = "",
                    content = "",
                    isSaved = true
                )
            )

    fun toggleSaved(id: Long?){
        viewModelScope.launch {
            newsRepository.toggleSaved(id)
        }
    }
}
