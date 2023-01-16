package com.example.newscompose.ui.newsDetails.mapper

import com.example.newscompose.model.NewsDetails
import com.example.newscompose.ui.newsDetails.NewsDetailsViewState

class NewsDetailsMapperImpl() : NewsDetailsMapper {
    override fun toNewsDetailsViewState(newsDetails: NewsDetails): NewsDetailsViewState =
        NewsDetailsViewState(
            newsDetails.news.id,
            newsDetails.news.source,
            newsDetails.news.headImageUrl,
            newsDetails.news.headline,
            newsDetails.news.date,
            newsDetails.author,
            newsDetails.text,
            newsDetails.news.isSaved
        )
}
