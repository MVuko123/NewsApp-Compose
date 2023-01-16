package com.example.newscompose.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.data.network.model.Source
import com.example.newscompose.data.repository.NewsRepository
import com.example.newscompose.ui.saved.mapper.SavedMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Thread.State

class SavedViewModel(
    private val newsRepository: NewsRepository,
    val savedMapper: SavedMapper
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

    fun toggleSaved(id: Long?){
        viewModelScope.launch {
            newsRepository.toggleSaved(id)
        }
    }
}
