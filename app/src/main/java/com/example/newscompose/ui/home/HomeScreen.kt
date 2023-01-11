package com.example.newscompose.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newscompose.data.network.model.ApiNews
import com.example.newscompose.data.network.model.Source
import com.example.newscompose.navigation.NavigationItem
import com.example.newscompose.ui.components.NewsCard
import com.example.newscompose.ui.components.NewsCardViewState
import com.example.newscompose.ui.components.NewsLabel
import com.example.newscompose.ui.components.NewsLabelViewState
import com.example.newscompose.ui.theme.NewsComposeTheme
import org.koin.androidx.compose.getViewModel


@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    source: Source?,
    onNavigateToNewsDetails: (String) -> Unit
){
    val newsCategory: HomeCategoryViewState by homeViewModel.newsViewState.collectAsState()
    NewsScreen(homeViewState = newsCategory,
        title = "News",
        onNavigateToNewsDetails = onNavigateToNewsDetails,
        onSavedClick = { homeViewModel.toggleSaved(source) },
        onLabelClick = { homeViewModel.switchSeletectedCategory(it) }
    )
}

@Composable
fun NewsScreen(
    homeViewState: HomeCategoryViewState,
    title: String,
    modifier: Modifier = Modifier,
    onNavigateToNewsDetails: (String) -> Unit,
    onSavedClick: (String?) -> Unit,
    onLabelClick: (Int) -> Unit,
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Text(
            modifier = modifier.padding(start = 20.dp, top = 10.dp),
            text = title,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            modifier = Modifier.height(50.dp),
            contentPadding = PaddingValues(start = 10.dp)
        ) {
            items(
                items = homeViewState.newsCategory,
                key = { categories -> categories.itemId }
            ) { categories ->

                NewsLabel(
                    newsLabelViewState = NewsLabelViewState(
                        categories.itemId,
                        categories.isSelected,
                        categories.text
                    ),
                    onLabelClick = { onLabelClick(categories.itemId) }
                )
            }
        }
        LazyRow(
            modifier = Modifier.height(210.dp),
            contentPadding = PaddingValues(
                start = 10.dp,
                end = 12.dp,
            )
        ) {
            items(
                items = homeViewState.news,
                key = { news -> news.source?.id!! }
            ) { news ->
                NewsCard(
                    newsCardViewState = NewsCardViewState(
                        news.headImageUrl,
                        news.headline,
                        news.date,
                        news.isSaved),
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

@Preview
@Composable
fun NewsScreenPreview(){
    NewsComposeTheme {
        val viewModel: HomeViewModel = getViewModel()
        NewsScreen(
            homeViewState = viewModel.newsViewState.collectAsState().value,
            title = "News",
            onNavigateToNewsDetails = {it},
            onSavedClick = { },
            onLabelClick = {  }
        )
    }
}

