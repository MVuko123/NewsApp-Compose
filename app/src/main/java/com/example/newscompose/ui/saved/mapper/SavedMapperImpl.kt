package com.example.newscompose.ui.saved.mapper

import com.example.newscompose.model.News
import com.example.newscompose.ui.components.NewsCardViewState
import com.example.newscompose.ui.saved.SavedNewsViewState
import com.example.newscompose.ui.saved.SavedViewState

class SavedMapperImpl() : SavedMapper {
    override fun toSavedViewState(savedNews: List<News>): SavedViewState =
        SavedViewState(
            savedNews.map { news ->
                SavedNewsViewState(
                    news.url,
                    NewsCardViewState(
                        news.headImageUrl,
                        news.headline,
                        news.date,
                        news.isSaved
                    )
                )
            }
        )
}
