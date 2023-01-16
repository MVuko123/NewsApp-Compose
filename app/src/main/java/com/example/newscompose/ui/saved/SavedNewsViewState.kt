package com.example.newscompose.ui.saved

import com.example.newscompose.data.network.model.Source
import com.example.newscompose.ui.components.NewsCardViewState

data class SavedNewsViewState(
    val id: Long?,
    val newsCard: NewsCardViewState
)

data class SavedViewState(
    val savedNewsViewState: List<SavedNewsViewState> = listOf()
)
