package com.example.newscompose.navigation

import com.example.newscompose.R
import com.example.newscompose.data.network.model.Source
import com.example.newscompose.model.News
import okhttp3.Request

const val HOME_ROUTE = "Home"
const val SAVED_ROUTE = "Saved"
const val NEWS_DETAILS_ROUTE = "NewsDetails"
const val NEWS_ID_KEY = "title"
const val NEWS_DETAILS_ROUTE_WITH_PARAMS = "$NEWS_DETAILS_ROUTE/{$NEWS_ID_KEY}"

sealed class NavigationItem(
    override val route: String,
    val selectedIconId: Int,
    val unselectedIconId: Int,
    val labelId: Int,
): NewsAppDestination(route) {
    object HomeDestination : NavigationItem(
        route = HOME_ROUTE,
        selectedIconId = R.drawable.ic_baseline_home_24,
        unselectedIconId = R.drawable.ic_outline_home_24,
        labelId = R.string.home,
    )

    object SavedDestination : NavigationItem(
        route = SAVED_ROUTE,
        selectedIconId = R.drawable.ic_baseline_bookmark_24,
        unselectedIconId = R.drawable.ic_baseline_bookmark_border_24,
        labelId = R.string.saved,
    )

    object NewsDetailsDestination : NewsAppDestination(NEWS_DETAILS_ROUTE_WITH_PARAMS){
        fun createNavigationRoute(source: Source?): String = "$NEWS_DETAILS_ROUTE/${source?.name}"
    }
}
