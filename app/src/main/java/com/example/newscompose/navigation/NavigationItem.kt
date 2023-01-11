package com.example.newscompose.navigation

import com.example.newscompose.data.network.model.Source

const val NEWS_DETAILS_ROUTE = "NewsDetails"
const val NEWS_ID_KEY = "newsId"
const val NEWS_DETAILS_ROUTE_WITH_PARAMS = "$NEWS_DETAILS_ROUTE/{$NEWS_ID_KEY}"

sealed class NavigationItem(
    override val route: String
): NewsAppDestination(route) {
    object NewsDetailsDestination : NewsAppDestination(NEWS_DETAILS_ROUTE_WITH_PARAMS){
        fun createNavigationRoute(source: Source?): String = "$NEWS_DETAILS_ROUTE/$source"
    }
}
