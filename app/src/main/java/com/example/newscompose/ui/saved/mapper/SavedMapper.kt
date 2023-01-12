package com.example.newscompose.ui.saved.mapper

import com.example.newscompose.model.News
import com.example.newscompose.ui.saved.SavedViewState

interface SavedMapper{
    fun toSavedViewState(savedNews: List<News>): SavedViewState
}
