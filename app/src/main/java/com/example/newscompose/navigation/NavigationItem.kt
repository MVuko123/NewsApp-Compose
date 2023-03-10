package com.example.newscompose.navigation

import com.example.newscompose.R

const val HOME_ROUTE = "Home"
const val SAVED_ROUTE = "Saved"
const val SEARCH_ROUTE = "Search"
const val NEWS_DETAILS_ROUTE = "NewsDetails"

sealed class NavigationItem(
    override val route: String,
    val selectedIconId: Int,
    val unselectedIconId: Int,
    val labelId: Int,
) : NewsAppDestination(route) {
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

    object SearchDestination : NavigationItem(
        route = SEARCH_ROUTE,
        selectedIconId = R.drawable.ic_baseline_search_24,
        unselectedIconId = R.drawable.ic_baseline_search_24,
        labelId = R.string.search
    )
}
