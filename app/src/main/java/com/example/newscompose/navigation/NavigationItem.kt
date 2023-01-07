package com.example.newscompose.navigation

const val NEWS_DETAILS_ROUTE = "NewsDetails"
const val NEWS_ID_KEY = "newsId"
const val NEWS_DETAILS_ROUTE_WITH_PARAMS = "$NEWS_DETAILS_ROUTE/{$NEWS_ID_KEY}"

sealed class NavigationItem(
    override val route: String
): NewsAppDestination(route) {
    object NewsDetailsDestination : NewsAppDestination(NEWS_DETAILS_ROUTE_WITH_PARAMS){
        fun createNavigationRoute(newsId: Int): String = "$NEWS_DETAILS_ROUTE/$newsId"
    }
}
