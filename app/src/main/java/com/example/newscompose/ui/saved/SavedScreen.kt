package com.example.newscompose.ui.saved

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newscompose.navigation.NavigationItem
import com.example.newscompose.ui.components.NewsCard

@Composable
fun SavedScreen(
    savedViewState: SavedViewState,
    modifier: Modifier = Modifier,
    onNavigateToNewsDetails: (String) -> Unit,
    onSavedClick: (String?) -> Unit,
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        LazyRow(
            modifier = modifier.height(210.dp),
            contentPadding = PaddingValues(
                start = 10.dp,
                end = 12.dp,
            )
        ) {
            items(
                items = savedViewState.savedNewsViewState,
                key = { news -> news.source?.id!! }
            ) { news ->
                NewsCard(
                    newsCardViewState = news.newsCard,
                    toNewsDetails = {
                        onNavigateToNewsDetails(NavigationItem.NewsDetailsDestination.createNavigationRoute(
                            news.source
                        ))
                    },
                    modifier = Modifier
                        .height(200.dp)
                        .width(150.dp),
                    onSavedClick = { onSavedClick(news.source?.id) }
                )
            }
        }
    }
}

