package com.example.newscompose.ui.newsDetails.mapper

import com.example.newscompose.model.NewsDetails
import com.example.newscompose.ui.newsDetails.NewsDetailsViewState

interface NewsDetailsMapper {
    fun toNewsDetailsViewState(newsDetails: NewsDetails): NewsDetailsViewState
}
