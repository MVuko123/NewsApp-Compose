package com.example.newscompose.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.data.repository.NewsRepository
import com.example.newscompose.ui.saved.mapper.SavedMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SavedViewModel(
    private val newsRepository: NewsRepository,
    val savedMapper: SavedMapper,
) : ViewModel() {
    private val _savedNewsViewState = MutableStateFlow(SavedViewState())
    val savedNewsViewState: StateFlow<SavedViewState> =
        newsRepository.savedNews()
            .map { news ->
                savedMapper.toSavedViewState(news)
            }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                _savedNewsViewState.value
            )

    fun toggleSaved(url: String) {
        viewModelScope.launch {
            newsRepository.toggleSaved(url)
        }
    }
}
